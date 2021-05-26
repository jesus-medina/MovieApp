package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.LocalDataMovieType
import com.backbase.assignment.data.remote.RemoteDataDetailedMostPopularMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface RemoteDataDetailedMostPopularMovieToLocalDataMovieMapper :
    Mapper<RemoteDataDetailedMostPopularMovie, LocalDataMovie>

class RemoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl @Inject constructor() :
    RemoteDataDetailedMostPopularMovieToLocalDataMovieMapper {
    override fun map(input: RemoteDataDetailedMostPopularMovie): LocalDataMovie = with(input) {
        val id = id ?: throw MapperException()
        val posterPath = poster_path ?: throw MapperException()
        val runtime = runtime ?: throw MapperException()
        val title = original_title ?: throw MapperException()
        val voteAverage = ((vote_average ?: throw MapperException()) * 10f).toLong()
        val overview = overview ?: throw MapperException()
        val releaseDate = release_date ?: throw MapperException()
        val genres = genres ?: throw MapperException()

        LocalDataMovie(
            id,
            posterPath,
            runtime.toLong(),
            title,
            voteAverage,
            overview,
            releaseDate,
            LocalDataMovieType.MostPopular,
            genres.map { it.name ?: "Unknown" }
        )
    }
}