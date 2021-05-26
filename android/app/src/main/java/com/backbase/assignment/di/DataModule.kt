package com.backbase.assignment.di

import com.backbase.assignment.data.local.MovieLocalDataSource
import com.backbase.assignment.data.local.MovieLocalDataSourceImpl
import com.backbase.assignment.data.remote.MovieRemoteDataSource
import com.backbase.assignment.data.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    fun bindsMovieRemoteDataSource(movieLocalDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}