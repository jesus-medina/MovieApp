package com.backbase.assignment.data.local

import com.mupper.personlist.MovieQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MovieLocalDataSource {
    fun getNowPlayingMovies(): Flow<List<LocalDataMovie>>
    fun getMostPopularMovies(): Flow<List<LocalDataMovie>>
    fun getMovieById(id: String): Flow<LocalDataMovie>
    fun addMovies(movies: List<LocalDataMovie>)
}

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieQueries: MovieQueries
) : MovieLocalDataSource {
    override fun getNowPlayingMovies(): Flow<List<LocalDataMovie>> =
        movieQueries.getNowPlayingMovies().asFlow().mapToList()

    override fun getMostPopularMovies(): Flow<List<LocalDataMovie>> =
        movieQueries.getMostPopularMovies().asFlow().mapToList()

    override fun getMovieById(id: String): Flow<LocalDataMovie> =
        movieQueries.getMovieById(id).asFlow().mapToOne()

    override fun addMovies(movies: List<LocalDataMovie>) {
        movies.forEach(::insertOrReplaceMovie)
    }

    private fun insertOrReplaceMovie(localDataMovie: LocalDataMovie) {
        with(localDataMovie) {
            movieQueries.insertOrReplaceMovie(id, poster_path, duration, title, rating, overview, release_date, movie_type, genres)
        }
    }
}