package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import kotlinx.coroutines.flow.Flow

interface GetMostPopularMoviesUseCase {
    operator fun invoke(): Flow<List<DomainMovie.DomainMostPopularMovie>>
}