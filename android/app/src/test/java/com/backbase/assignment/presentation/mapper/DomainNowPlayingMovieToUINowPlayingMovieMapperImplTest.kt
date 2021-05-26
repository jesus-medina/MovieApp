package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class DomainNowPlayingMovieToUINowPlayingMovieMapperImplTest {
    lateinit var domainNowPlayingMovie: DomainMovie.DomainNowPlayingMovie

    lateinit var domainNowPlayingMovieToUINowPlayingMovieMapperImpl: DomainNowPlayingMovieToUINowPlayingMovieMapperImpl

    @Before
    fun setUp() {
        domainNowPlayingMovieToUINowPlayingMovieMapperImpl =
            DomainNowPlayingMovieToUINowPlayingMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        domainNowPlayingMovie = createDomainNowPlayingMovie(id = expectedId)

        // When
        val result = domainNowPlayingMovieToUINowPlayingMovieMapperImpl.map(domainNowPlayingMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnExpectedPosterImageGivenPosterImageOnInputReturnsExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        domainNowPlayingMovie = createDomainNowPlayingMovie(posterImage = expectedPosterImage)

        // When
        val result = domainNowPlayingMovieToUINowPlayingMovieMapperImpl.map(domainNowPlayingMovie).posterImage

        // Then
        assertThat(result, `is`(expectedPosterImage))
    }

    private fun createDomainNowPlayingMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
    ): DomainMovie.DomainNowPlayingMovie =
        DomainMovie.DomainNowPlayingMovie(id, posterImage)
}