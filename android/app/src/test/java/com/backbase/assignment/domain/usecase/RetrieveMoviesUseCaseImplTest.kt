package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrieveMoviesUseCaseImplTest {
    @RelaxedMockK
    lateinit var movieRepository: MovieRepository

    @InjectMockKs
    lateinit var retrieveMoviesUseCaseImpl: RetrieveMoviesUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldCallRetrieveMoviesOnMovieRepository() = runBlockingTest {
        // When
        retrieveMoviesUseCaseImpl()

        // Then
        coVerify { movieRepository.retrieveMovies() }
    }
}