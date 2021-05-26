package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random

class DomainMostPopularMovieToUIMostPopularMovieMapperImplTest {
    lateinit var domainMostPopularMovie: DomainMovie.DomainMostPopularMovie

    lateinit var domainMostPopularMovieToUIMostPopularMovieMapperImpl: DomainMostPopularMovieToUIMostPopularMovieMapperImpl

    @Before
    fun setUp() {
        domainMostPopularMovieToUIMostPopularMovieMapperImpl = DomainMostPopularMovieToUIMostPopularMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        domainMostPopularMovie = createDomainMostPopularMovie(id = expectedId)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnExpectedPosterImageGivenPosterImageOnInputReturnsExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        domainMostPopularMovie = createDomainMostPopularMovie(posterImage = expectedPosterImage)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).posterImage

        // Then
        assertThat(result, `is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnExpectedTitleGivenTitleOnInputReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        domainMostPopularMovie = createDomainMostPopularMovie(title = expectedTitle)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).title

        // Then
        assertThat(result, `is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnExpectedRatingGivenRatingOnInputReturnsExpectedRating() {
        // Given
        val expectedRating = Random.nextInt()
        domainMostPopularMovie = createDomainMostPopularMovie(rating = expectedRating)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).rating

        // Then
        assertThat(result, `is`(expectedRating))
    }

    @Test
    fun mapShouldReturnExpectedDurationGivenRatingOnInputReturnsDuration() {
        // Given
        val duration = Random.nextInt()
        domainMostPopularMovie = createDomainMostPopularMovie(duration = duration)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).duration

        // Then
        val expectedDuration = duration.formatDurationInMinutes()
        assertThat(result, `is`(expectedDuration))
    }

    @Test
    fun mapShouldReturnExpectedReleaseDateGivenRatingOnInputReturnsReleaseDate() {
        // Given
        val expectedReleaseDate = Date()
        domainMostPopularMovie = createDomainMostPopularMovie(releaseDate = expectedReleaseDate)

        // When
        val result = domainMostPopularMovieToUIMostPopularMovieMapperImpl.map(domainMostPopularMovie).releaseDate

        // Then
        assertThat(result, `is`(expectedReleaseDate))
    }

    private fun createDomainMostPopularMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(),
        duration: Int = Random.nextInt(),
        releaseDate: Date = Date(),
    ) = DomainMovie.DomainMostPopularMovie(
        id, posterImage, title, rating, duration, releaseDate
    )

    private fun Int.formatDurationInMinutes(): String {
        val hours: Int = this / 60
        val minutes: Int = this % 60

        return (if (hours == 0) "" else " ${hours}h") +
                if (minutes == 0) "" else " ${minutes}m"
    }
}