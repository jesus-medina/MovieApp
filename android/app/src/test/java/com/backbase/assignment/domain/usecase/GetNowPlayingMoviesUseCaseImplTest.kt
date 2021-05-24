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


@ExperimentalCoroutinesApi
class GetNowPlayingMoviesUseCaseImplTest {
    @RelaxedMockK
    lateinit var movieRepository: MovieRepository

    @InjectMockKs
    lateinit var getNowPlayingMoviesUseCaseImpl: GetNowPlayingMoviesUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldReturnExpectedListOfDomainNowPlayingMoviesGivenGetNowPlayingMoviesOnMovieRepositoryReturnsExpectedListOfDomainNowPlayingMovies() =
        runBlockingTest {
            // Given
            val expectedListOfDomainNowPlayingMovies =
                listOf(DomainMovie.DomainNowPlayingMovie("posterImage"))
            coEvery { movieRepository.getNowPlayingMovies() } returns flowOf(
                expectedListOfDomainNowPlayingMovies
            )

            // When
            val result = getNowPlayingMoviesUseCaseImpl().first()

            // Then
            assertThat(result, `is`(expectedListOfDomainNowPlayingMovies))
        }
}