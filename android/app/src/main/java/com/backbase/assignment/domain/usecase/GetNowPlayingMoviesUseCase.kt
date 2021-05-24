package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainNowPlayingMovie>>
}

class GetNowPlayingMoviesUseCaseImpl @Inject constructor() : GetNowPlayingMoviesUseCase {
    override fun invoke(): Flow<List<DomainMovie.DomainNowPlayingMovie>> {
        TODO("Not yet implemented")
    }
}