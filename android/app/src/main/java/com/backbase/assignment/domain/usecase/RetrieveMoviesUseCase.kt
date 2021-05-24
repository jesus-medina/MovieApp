package com.backbase.assignment.domain.usecase

import javax.inject.Inject

interface RetrieveMoviesUseCase {
    suspend operator fun invoke()
}

class RetrieveMoviesUseCaseImpl @Inject constructor() : RetrieveMoviesUseCase {
    override suspend fun invoke() {
        TODO("RetrieveMoviesUseCaseImpl Not yet implemented")
    }
}