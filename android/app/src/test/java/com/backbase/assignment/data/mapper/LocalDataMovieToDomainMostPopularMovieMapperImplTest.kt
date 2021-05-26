package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.LocalDataMovieType
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.util.*
import javax.inject.Named
import kotlin.random.Random


class LocalDataMovieToDomainMostPopularMovieMapperImplTest {
    @RelaxedMockK
    @Named("dataReleaseDateFormat")
    lateinit var dateFormat: DateFormat

    @InjectMockKs
    lateinit var localDataMovieToDomainMostPopularMovieMapperImpl: LocalDataMovieToDomainMostPopularMovieMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(id = expectedId)

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithExpectedPosterImageGivenIdOnInputReturnsExpectedPosterImage() {
        // Given
        val posterImage = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(posterPath = posterImage)

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).posterImage

        // Then
        val expectedPosterImage = "$ENDPOINT_SUFFIX_IMAGE$posterImage"
        assertThat(result, `is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithExpectedTitleGivenIdOnInputReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(title = expectedTitle)

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).title

        // Then
        assertThat(result, `is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithExpectedRatingGivenIdOnInputReturnsExpectedRating() {
        // Given
        val expectedRating = Random.nextLong()
        val localDataMovie = createLocalDataMovie(rating = expectedRating)

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).rating

        // Then
        assertThat(result, `is`(expectedRating.toInt()))
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithExpectedDurationGivenIdOnInputReturnsExpectedDuration() {
        // Given
        val expectedDuration = Random.nextLong()
        val localDataMovie = createLocalDataMovie(duration = expectedDuration)

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).duration

        // Then
        assertThat(result, `is`(expectedDuration.toInt()))
    }

    @Test
    fun mapShouldReturnDomainMostPopularMovieWithexpEctedReleaseDateGivenIdOnInputReturnsExpectedReleaseDate() {
        // Given
        val releaseDate = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(release_date = releaseDate)
        val expectedReleaseDate = Date(Random.nextLong())
        every { dateFormat.parse(releaseDate) } returns expectedReleaseDate

        // When
        val result = localDataMovieToDomainMostPopularMovieMapperImpl.map(localDataMovie).releaseDate

        // Then
        assertThat(result, `is`(expectedReleaseDate))
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenParseOnDateFormatReturnsNull() {
        // Given
        every { dateFormat.parse(any()) } returns null

        // When
        localDataMovieToDomainMostPopularMovieMapperImpl.map(mockk(relaxed = true))
    }

    private fun createLocalDataMovie(
        id: String = "${Random.nextInt()}",
        posterPath: String = "${Random.nextInt()}",
        duration: Long = Random.nextLong(),
        title: String = "${Random.nextInt()}",
        rating: Long = Random.nextLong(),
        overview: String = "${Random.nextInt()}",
        release_date: String = "${Random.nextInt()}",
        movie_type: LocalDataMovieType = listOf(
            LocalDataMovieType.Normal,
            LocalDataMovieType.NowPlaying,
            LocalDataMovieType.MostPopular
        ).random(),
        genres: List<String> = listOf(
            "Action",
            "Adventure",
            "Animation",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "History",
            "Horror",
            "Music",
            "Mystery",
            "Romance",
            "ScienceFiction",
            "TVMovie",
            "Thriller",
            "War",
            "Western"
        )
    ): LocalDataMovie =
        LocalDataMovie(
            id,
            posterPath,
            duration,
            title,
            rating,
            overview,
            release_date,
            movie_type,
            genres
        )

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}