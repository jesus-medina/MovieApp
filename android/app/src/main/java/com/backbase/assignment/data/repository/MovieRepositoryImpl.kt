package com.backbase.assignment.data.repository

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor() : MovieRepository {
    override fun retrieveMovies() {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): Flow<List<DomainMovie.DomainNowPlayingMovie>> {
        TODO("Not yet implemented")
    }

    override fun getMostPopularMovies(): Flow<List<DomainMovie.DomainMostPopularMovie>> {
        TODO("Not yet implemented")
    }

    override fun getMovieById(id: String): DomainMovie.DomainDetailedPopularMovie {
        TODO("Not yet implemented")
    }
}