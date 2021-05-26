package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.repository.MovieRepository
import javax.inject.Inject

interface RetrieveMoviesUseCase {
    suspend operator fun invoke()
}

class RetrieveMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : RetrieveMoviesUseCase {
    override suspend fun invoke() {
        movieRepository.retrieveMovies()
    }
}