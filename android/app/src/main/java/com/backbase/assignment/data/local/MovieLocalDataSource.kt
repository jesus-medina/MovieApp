package com.backbase.assignment.data.local

import com.backbase.assignment.data.DataMovie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getNowPlayingMovies(): Flow<List<DataMovie>>
    fun getMostPopularMovies(): Flow<List<DataMovie>>
    fun getMovieById(id: String): DataMovie
}