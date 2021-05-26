package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainGenre
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIGenre
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface DomainDetailedPopularMovieToUIDetailedPopularMovieMapper :
    Mapper<DomainMovie.DomainDetailedMovie, UIMovie.UIDetailedMovie>

class DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl @Inject constructor() :
    DomainDetailedPopularMovieToUIDetailedPopularMovieMapper {
    override fun map(input: DomainMovie.DomainDetailedMovie): UIMovie.UIDetailedMovie =
        with(input) {
            UIMovie.UIDetailedMovie(
                id,
                "$ENDPOINT_SUFFIX_IMAGE$posterImage",
                title,
                rating,
                duration.formatDurationInMinutes(),
                releaseDate,
                overview,
                genres.map { it.toUIGenre() }
            )
        }

    private fun Int.formatDurationInMinutes(): String {
        val hours: Int = this / 60
        val minutes: Int = this % 60

        return (if (hours == 0) "" else " ${hours}h") +
                if (minutes == 0) "" else " ${minutes}m"
    }

    private fun DomainGenre.toUIGenre(): UIGenre = when (this) {
        DomainGenre.Action -> UIGenre.Action
        DomainGenre.Adventure -> UIGenre.Adventure
        DomainGenre.Animation -> UIGenre.Animation
        DomainGenre.Comedy -> UIGenre.Comedy
        DomainGenre.Crime -> UIGenre.Crime
        DomainGenre.Documentary -> UIGenre.Documentary
        DomainGenre.Drama -> UIGenre.Drama
        DomainGenre.Family -> UIGenre.Family
        DomainGenre.Fantasy -> UIGenre.Fantasy
        DomainGenre.History -> UIGenre.History
        DomainGenre.Horror -> UIGenre.Horror
        DomainGenre.Music -> UIGenre.Music
        DomainGenre.Mystery -> UIGenre.Mystery
        DomainGenre.Romance -> UIGenre.Romance
        DomainGenre.ScienceFiction -> UIGenre.ScienceFiction
        DomainGenre.TVMovie -> UIGenre.TVMovie
        DomainGenre.Thriller -> UIGenre.Thriller
        DomainGenre.War -> UIGenre.War
        DomainGenre.Western -> UIGenre.Western
    }

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}