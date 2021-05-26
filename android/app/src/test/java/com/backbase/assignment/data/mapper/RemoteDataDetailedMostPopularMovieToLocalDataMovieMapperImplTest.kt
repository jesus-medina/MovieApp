package com.backbase.assignment.data.mapper

import com.backbase.assignment.data.expection.MapperException
import com.backbase.assignment.data.local.LocalDataMovieType
import com.backbase.assignment.data.remote.RemoteDataDetailedMostPopularMovie
import com.backbase.assignment.data.remote.RemoteDataMovieGenre
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class RemoteDataDetailedMostPopularMovieToLocalDataMovieMapperImplTest {
    lateinit var remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl: RemoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl

    @Before
    fun setUp() {
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl =
            RemoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl()
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedIdGivenIdOnInputReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            id = expectedId
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedPosterImageGivenIdOnInputReturnsExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            posterImage = expectedPosterImage
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).poster_path

        // Then
        assertThat(result, `is`(expectedPosterImage))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedRuntimeGivenIdOnInputReturnsExpectedRuntime() {
        // Given
        val expectedRuntime = Random.nextInt()
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            runtime = expectedRuntime
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).duration

        // Then
        assertThat(result, `is`(expectedRuntime.toLong()))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedTitleGivenIdOnInputReturnsExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            title = expectedTitle
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).title

        // Then
        assertThat(result, `is`(expectedTitle))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedVoteAverageGivenIdOnInputReturnsExpectedVoteAverage() {
        // Given
        val voteAverage = Random.nextFloat()
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            voteAverage = voteAverage
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).rating

        // Then
        val expectedVoteAverage = (voteAverage * 10f).toLong()
        assertThat(result, `is`(expectedVoteAverage))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedOverviewGivenIdOnInputReturnsExpectedOverview() {
        // Given
        val expectedOverview = "${Random.nextInt()}"
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            overview = expectedOverview
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).overview

        // Then
        assertThat(result, `is`(expectedOverview))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedReleaseDateGivenIdOnInputReturnsExpectedReleaseDate() {
        // Given
        val expectedReleaseDate = "${Random.nextInt()}"
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            releaseDate = expectedReleaseDate
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).release_date

        // Then
        assertThat(result, `is`(expectedReleaseDate))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedLocalDataMovieType() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie()

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).movie_type

        // Then
        assertThat(result, `is`(LocalDataMovieType.MostPopular))
    }

    @Test
    fun mapShouldReturnLocalDataMovieWithExpectedGenresGivenGenresOnInputReturnsAListOfRemoteDataMovieGenre() {
        // Given
        val genreName = "${Random.nextInt()}"
        val listOfRemoteDataMovieGenre = listOf(
            RemoteDataMovieGenre("id", genreName)
        )
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            genres = listOfRemoteDataMovieGenre
        )

        // When
        val result = remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        ).genres

        // Then
        assertThat(result, `is`(listOf(genreName)))
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenIdOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            id = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenPosterPathOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            posterImage = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenRuntimeOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            runtime = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenOriginalTitleOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            title = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenVoteAverageOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            voteAverage = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenOverviewOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            overview = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenReleaseDateOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            releaseDate = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    @Test(expected = MapperException::class)
    fun mapShouldThrowMapperExceptionGivenGenresOnInputReturnsNull() {
        // Given
        val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
            genres = null
        )

        // When
        remoteDataDetailedMostPopularMovieToLocalDataMovieMapperImpl.map(
            remoteDataDetailedMostPopularMovie
        )
    }

    private fun createRemoteDataDetailedMostPopularMovie(
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
    ) = RemoteDataDetailedMostPopularMovie(
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