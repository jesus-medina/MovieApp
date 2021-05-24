package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMostPopularMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainMostPopularMovie>>
}

class GetMostPopularMoviesUseCaseImpl @Inject constructor() : GetMostPopularMoviesUseCase {
    override fun invoke(): Flow<List<DomainMovie.DomainMostPopularMovie>> {
        TODO("Not yet implemented")
    }
}