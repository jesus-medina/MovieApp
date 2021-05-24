package com.backbase.assignment.presentation

import java.util.*

sealed class UIMovie(open val id: String, open val posterImage: String) {

    data class UINowPlayingMovie(override val id: String, override val posterImage: String) : UIMovie(id, posterImage)

    open class UIMostPopularMovie(
        id: String,
        posterImage: String,
        val title: String,
        val rating: Int,
        val duration: String,
        val releaseDate: Date
    ) : UIMovie(id, posterImage) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is UIMostPopularMovie) return false

            if (title != other.title) return false
            if (rating != other.rating) return false
            if (duration != other.duration) return false
            if (releaseDate != other.releaseDate) return false

            return true
        }

        override fun hashCode(): Int {
            var result = title.hashCode()
            result = 31 * result + rating
            result = 31 * result + duration.hashCode()
            result = 31 * result + releaseDate.hashCode()
            return result
        }
    }

    class UIDetailedPopularMovie(
        id: String,
        posterImage: String,
        title: String,
        rating: Int,
        duration: String,
        releaseDate: Date,
        val overview: String,
        val genres: List<UIGenre>
    ) : UIMostPopularMovie(id, posterImage, title, rating, duration, releaseDate)
}
