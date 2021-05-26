package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.LocalDataMovieType
import com.backbase.assignment.domain.DomainGenre
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.util.*
import javax.inject.Named
import kotlin.random.Random


class LocalDataMovieToDomainDetailedMovieMapperImplTest {
    @RelaxedMockK
    @Named("dataReleaseDateFormat")
    lateinit var dateFormat: DateFormat

    @InjectMockKs
    lateinit var localDataMovieToDomainDetailedMovieMapperImpl: LocalDataMovieToDomainDetailedMovieMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedIdGivenIdLocalDataMovieReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            id = expectedId
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedPosterImageGivenIdLocalDataMovieReturnsExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            posterPath = expectedPosterImage
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).posterImage

        // Then
        assertThat(result, `is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedTitleGivenIdLocalDataMovieReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            title = expectedTitle
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).title

        // Then
        assertThat(result, `is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedRatingGivenIdLocalDataMovieReturnsExpectedRating() {
        // Given
        val expectedRating = Random.nextLong()
        val localDataMovie = createLocalDataMovie(
            rating = expectedRating
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).rating

        // Then
        assertThat(result, `is`(expectedRating.toInt()))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedDurationGivenIdLocalDataMovieReturnsExpectedDuration() {
        // Given
        val expectedDuration = Random.nextLong()
        val localDataMovie = createLocalDataMovie(
            duration = expectedDuration
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).duration

        // Then
        assertThat(result, `is`(expectedDuration.toInt()))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedReleaseDateGivenIdLocalDataMovieReturnsExpectedReleaseDate() {
        // Given
        val releaseDate = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            release_date = releaseDate
        )
        val expectedReleaseDate = Date(Random.nextLong())
        every { dateFormat.parse(releaseDate) } returns expectedReleaseDate

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).releaseDate

        // Then
        assertThat(result, `is`(expectedReleaseDate))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedOverviewGivenIdLocalDataMovieReturnsExpectedOverview() {
        // Given
        val expectedOverview = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            overview = expectedOverview
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).overview

        // Then
        assertThat(result, `is`(expectedOverview))
    }

    @Test
    fun mapShouldReturnDomainDetailedMovieWithExpectedGenresGivenIdLocalDataMovieReturnsExpectedGenres() {
        // Given
        val genres = listOf(
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
        val localDataMovie = createLocalDataMovie(
            genres = genres
        )

        // When
        val result = localDataMovieToDomainDetailedMovieMapperImpl.map(localDataMovie).genres

        // Then
        val expectedGenres = listOf(
            DomainGenre.Action,
            DomainGenre.Adventure,
            DomainGenre.Animation,
            DomainGenre.Comedy,
            DomainGenre.Crime,
            DomainGenre.Documentary,
            DomainGenre.Drama,
            DomainGenre.Family,
            DomainGenre.Fantasy,
            DomainGenre.History,
            DomainGenre.Horror,
            DomainGenre.Music,
            DomainGenre.Mystery,
            DomainGenre.Romance,
            DomainGenre.ScienceFiction,
            DomainGenre.TVMovie,
            DomainGenre.Thriller,
            DomainGenre.War,
            DomainGenre.Western,
        )
        assertThat(result, `is`(expectedGenres))
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
}