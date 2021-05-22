package com.backbase.assignment.presentation

import java.util.*

sealed class UIMovie(posterImage: String) {

    class UINowPlayingMovie(posterImage: String) : UIMovie(posterImage)

    open class UIMostPopularMovie(
        posterImage: String,
        var title: String,
        var rating: Byte,
        var duration: Int,
        var releaseDate: Date
    ) : UIMovie(posterImage)

    class UIDetailedPopularMovie(
        posterImage: String,
        title: String,
        rating: Byte,
        duration: Int,
        releaseDate: Date,
        var overview: String,
        var genres: List<UIGenre>
    ) : UIMostPopularMovie(posterImage, title, rating, duration, releaseDate)
}
