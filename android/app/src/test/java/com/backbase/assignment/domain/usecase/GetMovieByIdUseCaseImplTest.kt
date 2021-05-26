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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random


@ExperimentalCoroutinesApi
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
    fun invokeShouldReturnExpectedDomainDetailedPopularMovieGivenGetMovieByIdOnMovieRepositoryReturnsExpectedDomainDetailedPopularMovieFromId() =
        runBlockingTest {
            // Given
            val id = "${Random.nextInt()}"
            val expectedDomainDetailedPopularMovie = DomainMovie.DomainDetailedMovie(
                "id",
                "posterImage",
                "title",
                100,
                1000,
                Date(),
                "overview",
                emptyList()
            )
            coEvery { movieRepository.getMovieById(id) } returns flowOf(
                expectedDomainDetailedPopularMovie
            )

            // When
            val result = getMovieByIdUseCaseImpl(id).first()

            // Then
            assertThat(result, `is`(expectedDomainDetailedPopularMovie))
        }
}