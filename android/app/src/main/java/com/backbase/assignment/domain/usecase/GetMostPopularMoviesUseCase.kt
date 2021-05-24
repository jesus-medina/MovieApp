package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMostPopularMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainMostPopularMovie>>
}

class GetMostPopularMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMostPopularMoviesUseCase {
    override fun invoke(): Flow<List<DomainMovie.DomainMostPopularMovie>> =
        movieRepository.getMostPopularMovies()
}