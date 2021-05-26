package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovieType
import com.backbase.assignment.data.remote.RemoteDataDetailedNowPlayingMovie
import com.backbase.assignment.data.remote.RemoteDataMovieGenre
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class RemoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImplTest {

    lateinit var remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl: RemoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl

    @Before
    fun setUp() {
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl =
            RemoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            id = expectedId
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).id

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedId))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedPosterImageGivenIdOnInputReturnsExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            posterImage = expectedPosterImage
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).poster_path

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedRuntimeGivenIdOnInputReturnsExpectedRuntime() {
        // Given
        val expectedRuntime = Random.nextInt()
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            runtime = expectedRuntime
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).duration

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedRuntime.toLong()))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedTitleGivenIdOnInputReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            title = expectedTitle
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).title

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedVoteAverageGivenIdOnInputReturnsExpectedVoteAverage() {
        // Given
        val voteAverage = Random.nextFloat()
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            voteAverage = voteAverage
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).rating

        // Then
        val expectedVoteAverage = (voteAverage * 10f).toLong()
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedVoteAverage))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedOverviewGivenIdOnInputReturnsExpectedOverview() {
        // Given
        val expectedOverview = "${Random.nextInt()}"
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            overview = expectedOverview
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).overview

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedOverview))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedReleaseDateGivenIdOnInputReturnsExpectedReleaseDate() {
        // Given
        val expectedReleaseDate = "${Random.nextInt()}"
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            releaseDate = expectedReleaseDate
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).release_date

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(expectedReleaseDate))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedLocalDataMovieType() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie()

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).movie_type

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(LocalDataMovieType.NowPlaying))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedGenresGivenGenresOnInputReturnsAListOfRemoteDataMovieGenre() {
        // Given
        val genreName = "${Random.nextInt()}"
        val listOfRemoteDataMovieGenre = listOf(
            RemoteDataMovieGenre("id", genreName)
        )
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            genres = listOfRemoteDataMovieGenre
        )

        // When
        val result = remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        ).genres

        // Then
        MatcherAssert.assertThat(result, CoreMatchers.`is`(listOf(genreName)))
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenIdOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            id = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenPosterPathOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            posterImage = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenRuntimeOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            runtime = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenOriginalTitleOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            title = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenVoteAverageOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            voteAverage = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenOverviewOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            overview = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenReleaseDateOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            releaseDate = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenGenresOnInputReturnsNull() {
        // Given
        val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
            genres = null
        )

        // When
        remoteDataDetailedNowPlayingMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedNowPlayingMovie
        )
    }

    private fun createRemoteDataDetailedNowPlayingMovie(
        id: String? = "${Random.nextInt()}",
        posterImage: String? = "${Random.nextInt()}",
        runtime: Int? = Random.nextInt(),
        title: String? = "${Random.nextInt()}",
        voteAverage: Float? = Random.nextFloat(),
        overview: String? = "${Random.nextInt()}",
        releaseDate: String? = "${Random.nextInt()}",
        genres: List<RemoteDataMovieGenre>? = listOf(
            RemoteDataMovieGenre("${Random.nextInt()}", "${Random.nextInt()}"),
        )
    ) = RemoteDataDetailedNowPlayingMovie(
        id,
        posterImage,
        runtime,
        title,
        voteAverage,
        overview,
        releaseDate,
        genres
    )
}