package com.backbase.assignment.domain

import java.util.*

sealed class DomainMovie(posterImage: String) {

    class DomainNowPlayingMovie(posterImage: String) : DomainMovie(posterImage)

    open class DomainMostPopularMovie(
        posterImage: String,
        var title: String,
        var rating: Int,
        var duration: Long,
        var releaseDate: Date
    ) : DomainMovie(posterImage)

    class DomainDetailedPopularMovie(
        posterImage: String,
        title: String,
        rating: Int,
        duration: Long,
        releaseDate: Date,
        var overview: String,
        var genres: List<DomainGenre>
    ) : DomainMostPopularMovie(posterImage, title, rating, duration, releaseDate)
}