package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface DomainMostPopularMovieToUIMostPopularMovieMapper :
    Mapper<DomainMovie.DomainMostPopularMovie, UIMovie.UIMostPopularMovie>

class DomainMostPopularMovieToUIMostPopularMovieMapperImpl @Inject constructor() :
    DomainMostPopularMovieToUIMostPopularMovieMapper {
    override fun map(input: DomainMovie.DomainMostPopularMovie): UIMovie.UIMostPopularMovie =
        with(input) {
            UIMovie.UIMostPopularMovie(
                id,
                posterImage,
                title,
                rating,
                duration.formatDurationInMinutes(),
                releaseDate
            )
        }

    private fun Int.formatDurationInMinutes(): String {
        val hours: Int = this / 60
        val minutes: Int = this % 60

        return (if (hours == 0) "" else " ${hours}h") +
                if (minutes == 0) "" else " ${minutes}m"
    }
}