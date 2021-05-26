package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.LocalDataMovieType
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class LocalDataMovieToDomainNowPlayingMovieMapperImplTest {
    lateinit var localDataMovieToDomainNowPlayingMovieMapperImpl: LocalDataMovieToDomainNowPlayingMovieMapperImpl

    @Before
    fun setUp() {
        localDataMovieToDomainNowPlayingMovieMapperImpl =
            LocalDataMovieToDomainNowPlayingMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnDomainNowPlayingMovieWithExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            id = expectedId
        )

        // When
        val result = localDataMovieToDomainNowPlayingMovieMapperImpl.map(localDataMovie).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnDomainNowPlayingMovieWithExpectedPosterImageGivenIdOnInputReturnsExpectedPosterImage() {
        // Given
        val posterImage = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            posterPath = posterImage
        )

        // When
        val result = localDataMovieToDomainNowPlayingMovieMapperImpl.map(localDataMovie).posterImage

        // Then
        val expectedPosterImage = "$ENDPOINT_SUFFIX_IMAGE$posterImage"
        assertThat(result, `is`(expectedPosterImage))
    }

    private fun createLocalDataMovie(
        id: String = "${Random.nextInt()}",
        posterPath: String = "${Random.nextInt()}",
        duration: Long = Random.nextLong(),
        title: String = "${Random.nextInt()}",
        rating: Long = Random.nextLong(),
        overview: String = "${Random.nextInt()}",
        release_date: String = "${Random.nextInt()}",
        movie_type: LocalDataMovieType = listOf(
            LocalDataMovieType.Normal,
            LocalDataMovieType.NowPlaying,
            LocalDataMovieType.MostPopular
        ).random(),
        genres: List<String> = listOf(
            "Action",
            "Adventure",
            "Animation",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "History",
            "Horror",
            "Music",
            "Mystery",
            "Romance",
            "ScienceFiction",
            "TVMovie",
            "Thriller",
            "War",
            "Western"
        )
    ): LocalDataMovie =
        LocalDataMovie(
            id,
            posterPath,
            duration,
            title,
            rating,
            overview,
            release_date,
            movie_type,
            genres
        )

    companion object {
        private const val ENDPOINT_SUFFIX_IMAGE = "https://image.tmdb.org/t/p/original"
    }
}