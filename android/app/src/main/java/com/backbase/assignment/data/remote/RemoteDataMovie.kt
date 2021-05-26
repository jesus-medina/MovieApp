package com.backbase.assignment.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteDataNowPlayingResponse(
    val results: List<RemoteDataNowPlayingMovie>
)

@Serializable
data class RemoteDataMostPopularResponse(
    val results: List<RemoteDataMostPopularMovie>
)

@Serializable
data class RemoteDataNowPlayingMovie(
    val id: String,
)

@Serializable
data class RemoteDataMostPopularMovie(
    val id: String,
)

@Serializable
data class RemoteDataDetailedNowPlayingMovie(
    val id: String?,
    val poster_path: String?,
    val runtime: Int?,
    val original_title: String?,
    val vote_average: Float?,
    val overview: String?,
    val release_date: String?,
    val genres: List<RemoteDataMovieGenre>?
)

@Serializable
data class RemoteDataDetailedMostPopularMovie(
    val id: String?,
    val poster_path: String?,
    val runtime: Int?,
    val original_title: String?,
    val vote_average: Float?,
    val overview: String?,
    val release_date: String?,
    val genres: List<RemoteDataMovieGenre>?
)

@Serializable
data class RemoteDataMovieGenre(
    val id: String?,
    val name: String?,
)
