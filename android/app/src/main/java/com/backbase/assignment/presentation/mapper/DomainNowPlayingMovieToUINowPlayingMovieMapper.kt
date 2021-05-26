package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface DomainNowPlayingMovieToUINowPlayingMovieMapper :
    Mapper<DomainMovie.DomainNowPlayingMovie, UIMovie.UINowPlayingMovie>

class DomainNowPlayingMovieToUINowPlayingMovieMapperImpl @Inject constructor() :
    DomainNowPlayingMovieToUINowPlayingMovieMapper {
    override fun map(input: DomainMovie.DomainNowPlayingMovie): UIMovie.UINowPlayingMovie =
        with(input) {
            UIMovie.UINowPlayingMovie(id, posterImage)
        }
}