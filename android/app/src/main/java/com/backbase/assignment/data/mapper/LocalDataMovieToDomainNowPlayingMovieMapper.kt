package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface LocalDataMovieToDomainNowPlayingMovieMapper :
    Mapper<LocalDataMovie, DomainMovie.DomainNowPlayingMovie>

class LocalDataMovieToDomainNowPlayingMovieMapperImpl @Inject constructor() :
    LocalDataMovieToDomainNowPlayingMovieMapper {
    override fun map(input: LocalDataMovie): DomainMovie.DomainNowPlayingMovie = with(input) {
        DomainMovie.DomainNowPlayingMovie(id, "$ENDPOINT_SUFFIX_IMAGE$poster_path")
    }

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}