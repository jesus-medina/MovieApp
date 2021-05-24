package com.backbase.assignment.di

import com.backbase.assignment.data.repository.MovieRepositoryImpl
import com.backbase.assignment.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}