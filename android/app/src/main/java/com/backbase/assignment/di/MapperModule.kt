package com.backbase.assignment.di

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
}