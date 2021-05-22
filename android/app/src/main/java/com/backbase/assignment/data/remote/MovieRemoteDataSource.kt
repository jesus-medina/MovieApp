package com.backbase.assignment.data.remote

import com.backbase.assignment.data.DataMovie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getNowPlayingMovies(): Flow<List<DataMovie>>
    fun getMostPopularMovies(): Flow<List<DataMovie>>
    fun getMovieById(id: String): DataMovie
}