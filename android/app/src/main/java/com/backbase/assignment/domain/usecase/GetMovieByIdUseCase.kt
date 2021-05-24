package com.backbase.assignment.domain.usecase

import com.backbase.assignment.domain.DomainMovie
import javax.inject.Inject

interface GetMovieByIdUseCase {
    operator fun invoke(id: String): DomainMovie.DomainDetailedPopularMovie
}

class GetMovieByIdUseCaseImpl @Inject constructor() : GetMovieByIdUseCase {
    override fun invoke(id: String): DomainMovie.DomainDetailedPopularMovie {
        TODO("Not yet implemented")
    }
}