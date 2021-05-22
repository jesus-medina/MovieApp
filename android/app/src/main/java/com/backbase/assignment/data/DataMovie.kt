package com.backbase.assignment.data

class DataMovie(
    var posterImage: String?,
    var title: String?,
    var rating: Int?,
    var duration: Int?,
    var releaseDate: String?,
    var overview: String?,
    var genres: List<DataGenre>
)