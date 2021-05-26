package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainGenre
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIGenre
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.math.abs
import kotlin.random.Random

class DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImplTest {
    lateinit var domainDetailedMovie: DomainMovie.DomainDetailedMovie

    lateinit var domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl: DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl

    @Before
    fun setUp() {
        domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl =
            DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        domainDetailedMovie = createDomainDetailedMovie(id = expectedId)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedPosterImageGivenPosterImageOnInputReturnsPosterImage() {
        // Given
        val posterImage = "${Random.nextInt()}"
        domainDetailedMovie = createDomainDetailedMovie(posterImage = posterImage)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).posterImage

        // Then
        val expectedPosterImage = "$ENDPOINT_SUFFIX_IMAGE$posterImage"
        assertThat(result, `is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedTitleGivenTitleOnInputReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        domainDetailedMovie = createDomainDetailedMovie(title = expectedTitle)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).title

        // Then
        assertThat(result, `is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedRatingGivenRatingOnInputReturnsExpectedRating() {
        // Given
        val expectedRating = Random.nextInt(0, 100)
        domainDetailedMovie = createDomainDetailedMovie(rating = expectedRating)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).rating

        // Then
        assertThat(result, `is`(expectedRating))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedDurationGivenDurationOnInputReturnsDuration() {
        // Given
        val duration = abs(Random.nextInt())
        domainDetailedMovie = createDomainDetailedMovie(duration = duration)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).duration

        // Then
        val expectedDuration = duration.formatDurationInMinutes()
        assertThat(result, `is`(expectedDuration))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedReleaseDateGivenReleaseDateOnInputReturnsExpectedReleaseDate() {
        // Given
        val expectedReleaseDate = Date()
        domainDetailedMovie = createDomainDetailedMovie(releaseDate = expectedReleaseDate)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).releaseDate

        // Then
        assertThat(result, `is`(expectedReleaseDate))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedOverviewGivenOverviewOnInputReturnsExpectedOverview() {
        // Given
        val expectedOverview = "${Random.nextInt()}"
        domainDetailedMovie = createDomainDetailedMovie(overview = expectedOverview)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).overview

        // Then
        assertThat(result, `is`(expectedOverview))
    }

    @Test
    fun mapShouldReturnUIDetailedMovieWithExpectedListOfUIGenreGivenGenresOnInputReturnsAListOfDomainGenres() {
        // Given
        val listOfDomainGenres = listOf(
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
        domainDetailedMovie = createDomainDetailedMovie(genres = listOfDomainGenres)

        // When
        val result =
            domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl.map(domainDetailedMovie).genres

        // Then
        val expectedListOfUIGenres: List<UIGenre> = listOf(
            UIGenre.Action,
            UIGenre.Adventure,
            UIGenre.Animation,
            UIGenre.Comedy,
            UIGenre.Crime,
            UIGenre.Documentary,
            UIGenre.Drama,
            UIGenre.Family,
            UIGenre.Fantasy,
            UIGenre.History,
            UIGenre.Horror,
            UIGenre.Music,
            UIGenre.Mystery,
            UIGenre.Romance,
            UIGenre.ScienceFiction,
            UIGenre.TVMovie,
            UIGenre.Thriller,
            UIGenre.War,
            UIGenre.Western,
        )
        assertThat(result, `is`(expectedListOfUIGenres))
    }

    private fun Int.formatDurationInMinutes(): String {
        val hours: Int = this / 60
        val minutes: Int = this % 60

        return (if (hours == 0) "" else " ${hours}h") +
                if (minutes == 0) "" else " ${minutes}m"
    }

    private fun createDomainDetailedMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(0, 100),
        duration: Int = abs(Random.nextInt()),
        releaseDate: Date = Date(),
        overview: String = "${Random.nextInt()}",
        genres: List<DomainGenre> = listOf(
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
    ) =
        DomainMovie.DomainDetailedMovie(
            id,
            posterImage,
            title,
            rating,
            duration,
            releaseDate,
            overview,
            genres
        )

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}