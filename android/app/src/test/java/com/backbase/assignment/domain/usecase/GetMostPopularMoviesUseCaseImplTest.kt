package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*


@ExperimentalCoroutinesApi
class GetMostPopularMoviesUseCaseImplTest {
    @RelaxedMockK
    lateinit var movieRepository: MovieRepository

    @InjectMockKs
    lateinit var getMostPopularMoviesUseCaseImpl: GetMostPopularMoviesUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldReturnExpectedListOfDomainMostPopularMoviesGivenGetMostPopularMoviesOnMovieRepositoryReturnsExpectedListOfDomainMostPopularMovies() = runBlockingTest {
        // Given
        val expectedListOfDomainMostPopularMovies =
            listOf(DomainMovie.DomainMostPopularMovie("posterImage", "title", 100, 1000, Date()))
        coEvery { movieRepository.getMostPopularMovies() } returns flowOf(
            expectedListOfDomainMostPopularMovies
        )

        // When
        val result = getMostPopularMoviesUseCaseImpl().first()

        // Then
        assertThat(result, `is`(expectedListOfDomainMostPopularMovies))
    }
}