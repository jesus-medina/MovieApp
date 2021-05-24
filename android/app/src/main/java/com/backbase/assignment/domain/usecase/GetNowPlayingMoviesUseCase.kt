package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainNowPlayingMovie>>
}

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetNowPlayingMoviesUseCase {
    override fun invoke(): Flow<List<DomainMovie.DomainNowPlayingMovie>> =
        movieRepository.getNowPlayingMovies()
}