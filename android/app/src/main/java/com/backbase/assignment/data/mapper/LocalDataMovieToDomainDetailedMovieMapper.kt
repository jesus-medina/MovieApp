package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.domain.DomainGenre
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.utils.mapper.Mapper
import java.text.DateFormat
import javax.inject.Inject
import javax.inject.Named

interface LocalDataMovieToDomainDetailedMovieMapper :
    Mapper<LocalDataMovie, DomainMovie.DomainDetailedMovie>

class LocalDataMovieToDomainDetailedMovieMapperImpl @Inject constructor(
    @Named("dataReleaseDateFormat")
    private val dateFormat: DateFormat
) :
    LocalDataMovieToDomainDetailedMovieMapper {
    override fun map(input: LocalDataMovie): DomainMovie.DomainDetailedMovie = with(input) {
        val releaseDate = dateFormat.parse(release_date) ?: throw MapperException()

        DomainMovie.DomainDetailedMovie(
            id,
            poster_path,
            title,
            rating.toInt(),
            duration.toInt(),
            releaseDate,
            overview,
            genres.map { it.toDomainGenre() }
        )
    }

    private fun String.toDomainGenre(): DomainGenre = when (this) {
        "Action" -> DomainGenre.Action
        "Adventure" -> DomainGenre.Adventure
        "Animation" -> DomainGenre.Animation
        "Comedy" -> DomainGenre.Comedy
        "Crime" -> DomainGenre.Crime
        "Documentary" -> DomainGenre.Documentary
        "Drama" -> DomainGenre.Drama
        "Family" -> DomainGenre.Family
        "Fantasy" -> DomainGenre.Fantasy
        "History" -> DomainGenre.History
        "Horror" -> DomainGenre.Horror
        "Music" -> DomainGenre.Music
        "Mystery" -> DomainGenre.Mystery
        "Romance" -> DomainGenre.Romance
        "ScienceFiction" -> DomainGenre.ScienceFiction
        "TVMovie" -> DomainGenre.TVMovie
        "Thriller" -> DomainGenre.Thriller
        "War" -> DomainGenre.War
        "Western" -> DomainGenre.Western
        else -> DomainGenre.Unknown
    }
}