package com.backbase.assignment.domain.repository

import com.backbase.assignment.domain.DomainMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun retrieveMovies()
    fun getNowPlayingMovies(): Flow<List<DomainMovie.DomainNowPlayingMovie>>
    fun getMostPopularMovies(): Flow<List<DomainMovie.DomainMostPopularMovie>>
    fun getMovieById(id: String): Flow<DomainMovie.DomainDetailedMovie>
}