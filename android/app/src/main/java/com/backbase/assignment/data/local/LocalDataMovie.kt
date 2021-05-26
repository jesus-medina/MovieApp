package com.backbase.assignment.data.local

import com.mupper.personlist.Movie
import kotlinx.serialization.Serializable

typealias LocalDataMovie = Movie

@Serializable
enum class LocalDataMovieType {
    NowPlaying,
    MostPopular,
    Normal
}