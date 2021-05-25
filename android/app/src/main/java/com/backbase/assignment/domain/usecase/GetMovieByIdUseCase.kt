package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import javax.inject.Inject

interface GetMovieByIdUseCase {
    operator fun invoke(id: String): DomainMovie.DomainDetailedPopularMovie
}

class GetMovieByIdUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieByIdUseCase {
    override fun invoke(id: String): DomainMovie.DomainDetailedPopularMovie =
        movieRepository.getMovieById(id)
}