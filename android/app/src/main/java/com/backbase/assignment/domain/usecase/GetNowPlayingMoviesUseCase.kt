package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import kotlinx.coroutines.flow.Flow

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainNowPlayingMovie>>
}