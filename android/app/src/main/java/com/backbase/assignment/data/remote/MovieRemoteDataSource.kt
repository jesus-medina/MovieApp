package com.backbase.assignment.data.remote

import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

interface MovieRemoteDataSource {
    suspend fun getNowPlayingMovies(): List<RemoteDataNowPlayingMovie>
    suspend fun getMostPopularMovies(): List<RemoteDataMostPopularMovie>
    suspend fun getDetailedNowPlayingMovieById(id: String): RemoteDataDetailedNowPlayingMovie
    suspend fun getDetailedMostPopularMovieById(id: String): RemoteDataDetailedMostPopularMovie
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieRemoteDataSource {
    override suspend fun getNowPlayingMovies(): List<RemoteDataNowPlayingMovie> =
        (httpClient.get("$ENDPOINT_THE_MOVIE_DB$ENDPOINT_SECTION_NOW_PLAYING_MOVIE$API_KEY") as RemoteDataNowPlayingResponse).results

    override suspend fun getMostPopularMovies(): List<RemoteDataMostPopularMovie> =
        (httpClient.get("$ENDPOINT_THE_MOVIE_DB$ENDPOINT_SECTION_MOST_POPULAR_MOVIE$API_KEY") as RemoteDataMostPopularResponse).results

    override suspend fun getDetailedNowPlayingMovieById(id: String): RemoteDataDetailedNowPlayingMovie =
        httpClient.get("$ENDPOINT_THE_MOVIE_DB$id$ENDPOINT_QUERY$API_KEY")

    override suspend fun getDetailedMostPopularMovieById(id: String): RemoteDataDetailedMostPopularMovie =
        httpClient.get("$ENDPOINT_THE_MOVIE_DB$id$ENDPOINT_QUERY$API_KEY")

    companion object {
        private const val ENDPOINT_QUERY = "?language=en-US&page=1&api_key="
        private const val ENDPOINT_THE_MOVIE_DB =
            "https://api.themoviedb.org/3/movie/"
        private const val ENDPOINT_SECTION_NOW_PLAYING_MOVIE =
            "now_playing$ENDPOINT_QUERY"
        private const val ENDPOINT_SECTION_MOST_POPULAR_MOVIE =
            "popular$ENDPOINT_QUERY"
        private const val API_KEY = "0ef323a42aa406b147aee0b891a5e3f9"
    }
}