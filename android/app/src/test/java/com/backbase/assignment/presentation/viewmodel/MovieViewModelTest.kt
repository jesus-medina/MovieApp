package com.backbase.assignment.presentation.viewmodel

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.usecase.GetMostPopularMoviesUseCase
import com.backbase.assignment.domain.usecase.GetMovieByIdUseCase
import com.backbase.assignment.domain.usecase.GetNowPlayingMoviesUseCase
import com.backbase.assignment.domain.usecase.RetrieveMoviesUseCase
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.mapper.DomainDetailedPopularMovieToUIDetailedPopularMovieMapper
import com.backbase.assignment.utils.mapper.ListMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    @RelaxedMockK
    lateinit var retrieveMoviesUseCase: RetrieveMoviesUseCase

    @RelaxedMockK
    lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @RelaxedMockK
    lateinit var domainNowPlayingMovieToUINowPlayingMovieListMapper: ListMapper<DomainMovie.DomainNowPlayingMovie, UIMovie.UINowPlayingMovie>

    @RelaxedMockK
    lateinit var getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase

    @RelaxedMockK
    lateinit var domainMostPopularMovieToUIMostPopularMovieListMapper: ListMapper<DomainMovie.DomainMostPopularMovie, UIMovie.UIMostPopularMovie>

    @RelaxedMockK
    lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @RelaxedMockK
    lateinit var domainDetailedPopularMovieToUIDetailedPopularMovieMapper: DomainDetailedPopularMovieToUIDetailedPopularMovieMapper

    @InjectMockKs
    lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun retrieveMoviesShouldCallInvokeOnRetrieveMoviesUseCase() = runBlockingTest {
        // When
        movieViewModel.retrieveMovies()

        // Then
        coVerify { retrieveMoviesUseCase() }
    }

    @Test
    fun getNowPlayingMoviesShouldReturnStateFlowWithExpectedListOfUINowPlayingMoviesGivenMapOnDomainNowPlayingMovieToUINowPlayingMovieListMapperReturnsExpectedListOfUINowPlayingMoviesFromAListOfDomainNowPlayingMoviesAsInput() =
        runBlockingTest {
            // Given
            val listOfDomainNowPlayingMoviesInput = listOf(
                DomainMovie.DomainNowPlayingMovie("id", "posterImage")
            )
            coEvery { getNowPlayingMoviesUseCase() } returns MutableStateFlow(
                listOfDomainNowPlayingMoviesInput
            )
            val expectedListOfUINowPlayingMovies =
                listOf(UIMovie.UINowPlayingMovie("id", "posterImage"))
            coEvery {
                domainNowPlayingMovieToUINowPlayingMovieListMapper.map(
                    listOfDomainNowPlayingMoviesInput
                )
            } returns expectedListOfUINowPlayingMovies

            // When
            val result = movieViewModel.getNowPlayingMovies().value

            // Then
            assertThat(result, `is`(expectedListOfUINowPlayingMovies))
        }

    @Test
    fun getMostPopularMoviesShouldReturnStateFlowWithExpectedListOfUIMostPopularMoviesGivenMapOnDomainMostPopularMovieToUIMostPopularMovieListMapperReturnsExpectedListOfUIMostPopularMoviesFromAListOfDomainMostPopularMoviesAsInput() =
        runBlockingTest {
            // Given
            val listOfDomainMostPopularMoviesInput = listOf(
                DomainMovie.DomainMostPopularMovie("id", "posterImage", "title", 100, 100, Date())
            )
            coEvery { getMostPopularMoviesUseCase() } returns MutableStateFlow(
                listOfDomainMostPopularMoviesInput
            )
            val expectedListOfUIMostPopularMovies = listOf(
                UIMovie.UIMostPopularMovie(
                    "id",
                    "posterImage",
                    "title",
                    100,
                    "duration",
                    Date()
                )
            )
            coEvery {
                domainMostPopularMovieToUIMostPopularMovieListMapper.map(
                    listOfDomainMostPopularMoviesInput
                )
            } returns expectedListOfUIMostPopularMovies

            // When
            val result = movieViewModel.getMostPopularMovies().value

            // Then
            assertThat(result, `is`(expectedListOfUIMostPopularMovies))
        }

    @Test
    fun getMovieByIdShouldReturnExpectedUIDetailedPopularMovieGivenMapOnDomainDetailedPopularMovieToUIDetailedPopularMovieMapperReturnsExpectedUIDetailedPopularMovieFromDomainDetailedPopularMovieAndId() =
        runBlockingTest {
            // Given
            val id = "${Random.nextInt()}"
            val domainDetailedPopularMovie = DomainMovie.DomainDetailedMovie(
                "id",
                "posterImage",
                "title",
                100,
                1000,
                Date(),
                "overview",
                emptyList()
            )
            coEvery { getMovieByIdUseCase(id) } returns flowOf(domainDetailedPopularMovie)
            val expectedUIDetailedPopularMovie = UIMovie.UIDetailedMovie(
                "id",
                "posterImage",
                "title",
                100,
                "duration",
                Date(),
                "overview",
                emptyList()
            )
            coEvery {
                domainDetailedPopularMovieToUIDetailedPopularMovieMapper.map(
                    domainDetailedPopularMovie
                )
            } returns expectedUIDetailedPopularMovie

            // When
            val result = movieViewModel.getMovieById(id).value

            // Then
            assertThat(result, `is`(expectedUIDetailedPopularMovie))
        }
}