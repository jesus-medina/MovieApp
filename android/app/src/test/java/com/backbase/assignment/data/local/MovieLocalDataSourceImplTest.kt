package com.backbase.assignment.data.local

import com.mupper.personlist.Movie
import com.mupper.personlist.MovieQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MovieLocalDataSourceImplTest {
    @RelaxedMockK
    lateinit var movieQueries: MovieQueries

    @InjectMockKs
    lateinit var movieLocalDataSourceImpl: MovieLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.squareup.sqldelight.runtime.coroutines.FlowQuery")
    }

    @After
    fun tearDown() {
        unmockkStatic("kotlinx.coroutines.flow.FlowKt")
        unmockkStatic("com.squareup.sqldelight.runtime.coroutines.FlowQuery")
    }

    @Test
    fun getNowPlayingMoviesShouldReturnExpectedListOfLocalDataMoviesGivenGetNowPlayingMoviesOnMovieQueriesReturnsExpectedListOfLocalDataMovies() =
        runBlockingTest {
            // Given
            val query = mockk<Query<Movie>>()
            val flow = mockk<Flow<Query<Movie>>>()
            every { query.asFlow() } returns flow
            val expectedListOfLocalDataMovie = mockk<Flow<List<LocalDataMovie>>>()
            every { flow.mapToList() } returns expectedListOfLocalDataMovie
            every { movieQueries.getNowPlayingMovies() } returns query

            // When
            val result = movieLocalDataSourceImpl.getNowPlayingMovies()

            // Then
            assertThat(result, `is`(expectedListOfLocalDataMovie))
        }

    @Test
    fun getMostPopularMoviesShouldReturnExpectedListOfLocalDataMoviesGivenGetMostPopularMoviesOnMovieQueriesReturnsExpectedListOfLocalDataMovies() =
        runBlockingTest {
            // Given
            val query = mockk<Query<Movie>>()
            val flow = mockk<Flow<Query<Movie>>>()
            every { query.asFlow() } returns flow
            val expectedListOfLocalDataMovie = mockk<Flow<List<LocalDataMovie>>>()
            every { flow.mapToList() } returns expectedListOfLocalDataMovie
            every { movieQueries.getMostPopularMovies() } returns query

            // When
            val result = movieLocalDataSourceImpl.getMostPopularMovies()

            // Then
            assertThat(result, `is`(expectedListOfLocalDataMovie))
        }

    @Test
    fun getMovieByIdShouldReturnExpectedLocalDataMovieGivenGetMovieByIdOnMovieQueriesReturnsExpectedLocalDataMovieWithId() =
        runBlockingTest {
            // Given
            val query = mockk<Query<Movie>>()
            val flow = mockk<Flow<Query<Movie>>>()
            every { query.asFlow() } returns flow
            val expectedLocalDataMovie = mockk<Flow<LocalDataMovie>>()
            every { flow.mapToOne() } returns expectedLocalDataMovie
            val id = "${Random.nextInt()}"
            every { movieQueries.getMovieById(id) } returns query

            // When
            val result = movieLocalDataSourceImpl.getMovieById(id)

            // Then
            assertThat(result, `is`(expectedLocalDataMovie))
        }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedIdGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            id = expectedId
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                expectedId,
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedPosterImageGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedPosterImage() {
        // Given
        val expectedPosterImage = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            posterPath = expectedPosterImage
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                expectedPosterImage,
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedDurationGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedDuration() {
        // Given
        val expectedDuration = Random.nextLong()
        val localDataMovie = createLocalDataMovie(
            duration = expectedDuration
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                expectedDuration,
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedTitleGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            title = expectedTitle
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                expectedTitle,
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedRatingGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedRating() {
        // Given
        val expectedRating = Random.nextLong()
        val localDataMovie = createLocalDataMovie(
            rating = expectedRating
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                any(),
                expectedRating,
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedOverviewGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedOverview() {
        // Given
        val expectedOverview = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            overview = expectedOverview
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                any(),
                any(),
                expectedOverview,
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedReleaseDateGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedReleaseDate() {
        // Given
        val expectedReleaseDate = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie(
            release_date = expectedReleaseDate
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                expectedReleaseDate,
                any(),
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedMovieTypeGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedMovieType() {
        // Given
        val expectedMovieType = listOf(
            LocalDataMovieType.NowPlaying,
            LocalDataMovieType.MostPopular,
            LocalDataMovieType.Normal
        ).random()
        val localDataMovie = createLocalDataMovie(
            movie_type = expectedMovieType
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                expectedMovieType,
                any()
            )
        }
    }

    @Test
    fun addMoviesShouldCallInsertOrReplaceMovieWithExpectedGenresGivenAddMoviesReceiveAListOfLocalDataMovieWithExpectedGenres() {
        // Given
        val expectedGenres = listOf(
            "${Random.nextInt()}",
            "${Random.nextInt()}",
            "${Random.nextInt()}",
            "${Random.nextInt()}",
        )
        val localDataMovie = createLocalDataMovie(
            genres = expectedGenres
        )

        // When
        movieLocalDataSourceImpl.addMovies(listOf(localDataMovie))

        // Then
        verify {
            movieQueries.insertOrReplaceMovie(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                expectedGenres
            )
        }
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
}