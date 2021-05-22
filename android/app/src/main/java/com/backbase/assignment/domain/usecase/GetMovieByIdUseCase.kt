package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie

interface GetMovieByIdUseCase {
    operator fun invoke(id: String): DomainMovie.DomainDetailedPopularMovie
}