package com.backbase.assignment.data.repository

import com.backbase.assignment.data.local.LocalDataMovie
import com.backbase.assignment.data.local.LocalDataMovieType
import com.backbase.assignment.data.local.MovieLocalDataSource
import com.backbase.assignment.data.mapper.LocalDataMovieToDomainDetailedMovieMapper
import com.backbase.assignment.data.remote.*
import com.backbase.assignment.domain.DomainGenre
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.utils.mapper.ListMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.math.abs
import kotlin.random.Random


@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    @RelaxedMockK
    lateinit var movieLocalDataSource: MovieLocalDataSource

    @RelaxedMockK
    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @RelaxedMockK
    lateinit var remoteDataDetailedNowPlayingMovieToLocalDataMovieListMapper: ListMapper<RemoteDataDetailedNowPlayingMovie, LocalDataMovie>

    @RelaxedMockK
    lateinit var remoteDataDetailedMostPopularMovieToLocalDataMovieListMapper: ListMapper<RemoteDataDetailedMostPopularMovie, LocalDataMovie>

    @RelaxedMockK
    lateinit var localDataMovieToDomainNowPlayingMovieListMapper: ListMapper<LocalDataMovie, DomainMovie.DomainNowPlayingMovie>

    @RelaxedMockK
    lateinit var localDataMovieToDomainMostPopularMovieListMapper: ListMapper<LocalDataMovie, DomainMovie.DomainMostPopularMovie>

    @RelaxedMockK
    lateinit var localDataMovieToDomainDetailedMovieMapper: LocalDataMovieToDomainDetailedMovieMapper

    @InjectMockKs
    lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun retrieveMoviesShouldCallAddMoviesWithExpectedListOfLocalDataMoviesGivenMapsOnRemoteToLocalMovieListReturnsExpectedListOfLocalDataMovies() =
        runBlockingTest {
            // Given
            val nowPlayingId = "${Random.nextInt()}"
            val listOfGetNowPlayingMovies = listOf(
                createRemoteDataNowPlayingMovie(id = nowPlayingId)
            )
            coEvery { movieRemoteDataSource.getNowPlayingMovies() } returns listOfGetNowPlayingMovies
            val remoteDataDetailedNowPlayingMovie = createRemoteDataDetailedNowPlayingMovie(
                id = nowPlayingId
            )
            coEvery { movieRemoteDataSource.getDetailedNowPlayingMovieById(nowPlayingId) } returns remoteDataDetailedNowPlayingMovie

            val mostPopularId = "${Random.nextInt()}"
            val listOfGetMostPopularMovies = listOf(
                createRemoteDataMostPopularMovie(id = mostPopularId)
            )
            coEvery { movieRemoteDataSource.getMostPopularMovies() } returns listOfGetMostPopularMovies
            val remoteDataDetailedMostPopularMovie = createRemoteDataDetailedMostPopularMovie(
                id = mostPopularId
            )
            coEvery { movieRemoteDataSource.getDetailedMostPopularMovieById(mostPopularId) } returns remoteDataDetailedMostPopularMovie

            val listOfLocalDataNowPlayingMovies = listOf(createLocalDataMovie())
            every {
                remoteDataDetailedNowPlayingMovieToLocalDataMovieListMapper.map(
                    listOf(
                        remoteDataDetailedNowPlayingMovie
                    )
                )
            } returns listOfLocalDataNowPlayingMovies
            val listOfLocalDataMostPopularMovies = listOf(createLocalDataMovie())
            every {
                remoteDataDetailedMostPopularMovieToLocalDataMovieListMapper.map(
                    listOf(
                        remoteDataDetailedMostPopularMovie
                    )
                )
            } returns listOfLocalDataMostPopularMovies

            // When
            movieRepositoryImpl.retrieveMovies()

            // Then
            verify {
                movieLocalDataSource.addMovies(
                    listOf(
                        listOfLocalDataNowPlayingMovies,
                        listOfLocalDataMostPopularMovies
                    ).flatten()
                )
            }
        }

    @Test
    fun getNowPlayingMoviesShouldReturnExpectedListOfDomainNowPlayingMoviesGivenMapOnLocalDataMovieToDomainNowPlayingMovieListMapperReturnsExpectedListOfDomainNowPlayingMovies() =
        runBlockingTest {
            // Given
            val listOfLocalDataMovie = listOf(createLocalDataMovie())
            every { movieLocalDataSource.getNowPlayingMovies() } returns flowOf(listOfLocalDataMovie)
            val expectedListOfDomainNowPlayingMovies = listOf(createDomainNowPlayingMovie())
            every { localDataMovieToDomainNowPlayingMovieListMapper.map(listOfLocalDataMovie) } returns expectedListOfDomainNowPlayingMovies

            // When
            val result = movieRepositoryImpl.getNowPlayingMovies().first()

            // Then
            assertThat(result, `is`(expectedListOfDomainNowPlayingMovies))
        }

    @Test
    fun getMostPopularMoviesShouldReturnExpectedListOfDomainMostPopularMoviesGivenMapOnLocalDataMovieToDomainMostPopularMovieListMapperReturnsExpectedListOfDomainMostPopularMovies() =
        runBlockingTest {
            // Given
            val listOfLocalDataMovie = listOf(createLocalDataMovie())
            every { movieLocalDataSource.getMostPopularMovies() } returns flowOf(
                listOfLocalDataMovie
            )
            val expectedListOfDomainMostPopularMovies = listOf(createDomainMostPopularMovie())
            every { localDataMovieToDomainMostPopularMovieListMapper.map(listOfLocalDataMovie) } returns expectedListOfDomainMostPopularMovies

            // When
            val result = movieRepositoryImpl.getMostPopularMovies().first()

            // Then
            assertThat(result, `is`(expectedListOfDomainMostPopularMovies))
        }

    @Test
    fun getMovieByIdShouldReturnExpectedDomainDetailedMovieGivenMapOnLocalDataMovieToDomainDetailedMovieMapperReturnsExpectedDomainDetailedMovie() = runBlockingTest {
        // Given
        val id = "${Random.nextInt()}"
        val localDataMovie = createLocalDataMovie()
        every { movieLocalDataSource.getMovieById(id) } returns flowOf(localDataMovie)
        val expectedDomainDetailedMovie = createDomainDetailedMovie()
        every { localDataMovieToDomainDetailedMovieMapper.map(localDataMovie) } returns expectedDomainDetailedMovie

        // When
        val result = movieRepositoryImpl.getMovieById(id).first()

        // Then
        assertThat(result, `is`(expectedDomainDetailedMovie))
    }

    private fun createRemoteDataNowPlayingMovie(
        id: String = "${Random.nextInt()}",
    ) = RemoteDataNowPlayingMovie(id)

    private fun createRemoteDataDetailedNowPlayingMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        runtime: Int = Random.nextInt(),
        title: String = "${Random.nextInt()}",
        voteAverage: Float = Random.nextFloat(),
        overview: String = "${Random.nextInt()}",
        releaseDate: String = "${Random.nextInt()}",
        genres: List<RemoteDataMovieGenre> = listOf(
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

    private fun createRemoteDataMostPopularMovie(
        id: String = "${Random.nextInt()}",
    ) = RemoteDataMostPopularMovie(id)

    private fun createRemoteDataDetailedMostPopularMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        runtime: Int = Random.nextInt(),
        title: String = "${Random.nextInt()}",
        voteAverage: Float = Random.nextFloat(),
        overview: String = "${Random.nextInt()}",
        releaseDate: String = "${Random.nextInt()}",
        genres: List<RemoteDataMovieGenre> = listOf(
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

    private fun createDomainNowPlayingMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
    ): DomainMovie.DomainNowPlayingMovie =
        DomainMovie.DomainNowPlayingMovie(id, posterImage)

    private fun createDomainMostPopularMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(),
        duration: Int = Random.nextInt(),
        releaseDate: Date = Date(),
    ) = DomainMovie.DomainMostPopularMovie(
        id, posterImage, title, rating, duration, releaseDate
    )

    private fun createDomainDetailedMovie(
        id: String = "${Random.nextInt()}",
        posterImage: String = "${Random.nextInt()}",
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(0, 100),
        duration: Int = abs(Random.nextInt()),
        releaseDate: Date = Date(),
        overview: String = "${Random.nextInt()}",
        genres: List<DomainGenre> = listOf(
            DomainGenre.Action,
            DomainGenre.Adventure,
            DomainGenre.Animation,
            DomainGenre.Comedy,
            DomainGenre.Crime,
            DomainGenre.Documentary,
            DomainGenre.Drama,
            DomainGenre.Family,
            DomainGenre.Fantasy,
            DomainGenre.History,
            DomainGenre.Horror,
            DomainGenre.Music,
            DomainGenre.Mystery,
            DomainGenre.Romance,
            DomainGenre.ScienceFiction,
            DomainGenre.TVMovie,
            DomainGenre.Thriller,
            DomainGenre.War,
            DomainGenre.Western,
        )
    ) =
        DomainMovie.DomainDetailedMovie(
            id,
            posterImage,
            title,
            rating,
            duration,
            releaseDate,
            overview,
            genres
        )
}