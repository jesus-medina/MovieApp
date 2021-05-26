package com.backbase.assignment.data.repository

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.MovieLocalDataSource
import com.backbase.assignment.data.mapper.LocalDataMovieToDomainDetailedMovieMapper
import com.backbase.assignment.data.remote.MovieRemoteDataSource
import com.backbase.assignment.data.remote.RemoteDataDetailedMostPopularMovie
import com.backbase.assignment.data.remote.RemoteDataDetailedNowPlayingMovie
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.repository.MovieRepository
import com.backbase.assignment.utils.mapper.ListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val remoteDataDetailedNowPlayingMovieToLocalDataMovieListMapper: ListMapper<RemoteDataDetailedNowPlayingMovie, LocalDataMovie>,
    private val remoteDataDetailedMostPopularMovieToLocalDataMovieListMapper: ListMapper<RemoteDataDetailedMostPopularMovie, LocalDataMovie>,
    private val localDataMovieToDomainNowPlayingMovieListMapper: ListMapper<LocalDataMovie, DomainMovie.DomainNowPlayingMovie>,
    private val localDataMovieToDomainMostPopularMovieListMapper: ListMapper<LocalDataMovie, DomainMovie.DomainMostPopularMovie>,
    private val localDataMovieToDomainDetailedMovieMapper: LocalDataMovieToDomainDetailedMovieMapper
) : MovieRepository {
    override suspend fun retrieveMovies() {
        val listOfRemoteDataDetailedNowPlayingMovies: List<RemoteDataDetailedNowPlayingMovie> =
            movieRemoteDataSource.getNowPlayingMovies()
                .map { movieRemoteDataSource.getDetailedNowPlayingMovieById(it.id) }
        val listOfRemoteDataDetailedMostPopularMovies: List<RemoteDataDetailedMostPopularMovie> =
            movieRemoteDataSource.getMostPopularMovies()
                .map { movieRemoteDataSource.getDetailedMostPopularMovieById(it.id) }

        val listOfLocalDataMovie: List<LocalDataMovie> = listOf(
            remoteDataDetailedNowPlayingMovieToLocalDataMovieListMapper.map(
                listOfRemoteDataDetailedNowPlayingMovies
            ),
            remoteDataDetailedMostPopularMovieToLocalDataMovieListMapper.map(
                listOfRemoteDataDetailedMostPopularMovies
            )
        ).flatten()

        movieLocalDataSource.addMovies(listOfLocalDataMovie)
    }

    override fun getNowPlayingMovies(): Flow<List<DomainMovie.DomainNowPlayingMovie>> =
        movieLocalDataSource.getNowPlayingMovies()
            .map(localDataMovieToDomainNowPlayingMovieListMapper::map)

    override fun getMostPopularMovies(): Flow<List<DomainMovie.DomainMostPopularMovie>> =
        movieLocalDataSource.getMostPopularMovies()
            .map(localDataMovieToDomainMostPopularMovieListMapper::map)

    override fun getMovieById(id: String): Flow<DomainMovie.DomainDetailedMovie> =
        movieLocalDataSource.getMovieById(id)
            .map(localDataMovieToDomainDetailedMovieMapper::map)
}