package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.utils.mapper.Mapper
import java.text.DateFormat
import javax.inject.Inject
import javax.inject.Named

interface LocalDataMovieToDomainMostPopularMovieMapper :
    Mapper<LocalDataMovie, DomainMovie.DomainMostPopularMovie>

class LocalDataMovieToDomainMostPopularMovieMapperImpl @Inject constructor(
    @Named("dataReleaseDateFormat")
    private val dateFormat: DateFormat
) :
    LocalDataMovieToDomainMostPopularMovieMapper {
    override fun map(input: LocalDataMovie): DomainMovie.DomainMostPopularMovie = with(input) {
        val releaseDate = dateFormat.parse(release_date) ?: throw MapperException()

        DomainMovie.DomainMostPopularMovie(
            id,
            "$ENDPOINT_SUFFIX_IMAGE$poster_path",
            title,
            rating.toInt(),
            duration.toInt(),
            releaseDate
        )
    }

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}