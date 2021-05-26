package com.backbase.assignment.di

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.mapper.*
import com.backbase.assignment.data.remote.RemoteDataDetailedMostPopularMovie
import com.backbase.assignment.data.remote.RemoteDataDetailedNowPlayingMovie
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.mapper.DomainDetailedPopularMovieToUIDetailedPopularMovieMapper
import com.backbase.assignment.presentation.mapper.DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl
import com.backbase.assignment.presentation.mapper.DomainMostPopularMovieToUIMostPopularMovieMapperImpl
import com.backbase.assignment.presentation.mapper.DomainNowPlayingMovieToUINowPlayingMovieMapperImpl
import com.backbase.assignment.utils.mapper.ListMapper
import com.backbase.assignment.utils.mapper.ListMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun providesDomainNowPlayingMovieToUINowPlayingMovieListMapper(
        domainNowPlayingMovieToUINowPlayingMovieMapperImpl: DomainNowPlayingMovieToUINowPlayingMovieMapperImpl
    ): ListMapper<DomainMovie.DomainNowPlayingMovie, UIMovie.UINowPlayingMovie> =
        ListMapperImpl(domainNowPlayingMovieToUINowPlayingMovieMapperImpl)

    @Provides
    fun providesDomainMostPopularMovieToUIMostPopularMovieListMapper(
        domainMostPopularMovieToUIMostPopularMovieMapperImpl: DomainMostPopularMovieToUIMostPopularMovieMapperImpl
    ): ListMapper<DomainMovie.DomainMostPopularMovie, UIMovie.UIMostPopularMovie> =
        ListMapperImpl(domainMostPopularMovieToUIMostPopularMovieMapperImpl)

    @Provides
    fun providesDomainDetailedPopularMovieToUIDetailedPopularMovieMapper(
        domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl: DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl
    ): DomainDetailedPopularMovieToUIDetailedPopularMovieMapper =
        domainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl

    @Provides
    fun providesRemoteDataDetailedNowPlayingMovieToMovieListMapper(
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl: RemoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl
    ): ListMapper<RemoteDataDetailedNowPlayingMovie, LocalDataMovie> =
        ListMapperImpl(remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl)

    @Provides
    fun providesRemoteDataDetailedMostPopularMovieToMovieListMapper(
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl: RemoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl
    ): ListMapper<RemoteDataDetailedMostPopularMovie, LocalDataMovie> =
        ListMapperImpl(remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl)

    @Provides
    fun providesLocalDataMovieToDomainNowPlayingMovieMapper(
        localDataMovieToDomainNowPlayingMovieImpl: LocalDataMovieToDomainNowPlayingMovieMapperImpl
    ): ListMapper<LocalDataMovie, DomainMovie.DomainNowPlayingMovie> =
        ListMapperImpl(localDataMovieToDomainNowPlayingMovieImpl)

    @Provides
    fun providesLocalDataMovieToDomainMostPopularMovieMapper(
        localDataMovieToDomainMostPopularMovieImpl: LocalDataMovieToDomainMostPopularMovieMapperImpl
    ): ListMapper<LocalDataMovie, DomainMovie.DomainMostPopularMovie> =
        ListMapperImpl(localDataMovieToDomainMostPopularMovieImpl)

    @Provides
    fun providesLocalDataMovieToDomainDetailedMovieMapper(
        localDataMovieToDomainDetailedMovieMapperImpl: LocalDataMovieToDomainDetailedMovieMapperImpl
    ): LocalDataMovieToDomainDetailedMovieMapper =
        localDataMovieToDomainDetailedMovieMapperImpl
}