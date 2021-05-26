package com.backbase.assignment.di

import com.backbase.assignment.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PresentationModule {
    @Binds
    fun bindsRetrieveMoviesUseCase(retrieveMoviesUseCaseImpl: RetrieveMoviesUseCaseImpl): RetrieveMoviesUseCase

    @Binds
    fun bindsGetNowPlayingMoviesUseCase(getNowPlayingMoviesUseCaseImpl: GetNowPlayingMoviesUseCaseImpl): GetNowPlayingMoviesUseCase

    @Binds
    fun bindsGetMostPopularMoviesUseCase(getMostPopularMoviesUseCaseImpl: GetMostPopularMoviesUseCaseImpl): GetMostPopularMoviesUseCase

    @Binds
    fun bindsGetMovieByIdUseCase(getMovieByIdUseCaseImpl: GetMovieByIdUseCaseImpl): GetMovieByIdUseCase
}