package com.backbase.assignment.domain

import java.util.*

sealed class DomainMovie(val id: String, val posterImage: String) {

    class DomainNowPlayingMovie(id: String, posterImage: String) : DomainMovie(id, posterImage)

    open class DomainMostPopularMovie(
        id: String,
        posterImage: String,
        val title: String,
        val rating: Int,
        val duration: Int,
        val releaseDate: Date
    ) : DomainMovie(id, posterImage)

    class DomainDetailedMovie(
        id: String,
        posterImage: String,
        title: String,
        rating: Int,
        duration: Int,
        releaseDate: Date,
        val overview: String,
        val genres: List<DomainGenre>
    ) : DomainMostPopularMovie(id, posterImage, title, rating, duration, releaseDate)
}