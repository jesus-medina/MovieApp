package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random


class GetMovieByIdUseCaseImplTest {
    @RelaxedMockK
    lateinit var movieRepository: MovieRepository

    @InjectMockKs
    lateinit var getMovieByIdUseCaseImpl: GetMovieByIdUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldReturnExpectedDomainDetailedPopularMovieGivenGetMovieByIdOnMovieRepositoryReturnsExpectedDomainDetailedPopularMovieFromId() {
        // Given
        val id = "${Random.nextInt()}"
        val expectedDomainDetailedPopularMovie = DomainMovie.DomainDetailedPopularMovie(
            "posterImage",
            "title",
            100,
            1000,
            Date(),
            "overview",
            emptyList()
        )
        coEvery { movieRepository.getMovieById(id) } returns expectedDomainDetailedPopularMovie

        // When
        val result = getMovieByIdUseCaseImpl(id)

        // Then
        assertThat(result, `is`(expectedDomainDetailedPopularMovie))
    }
}