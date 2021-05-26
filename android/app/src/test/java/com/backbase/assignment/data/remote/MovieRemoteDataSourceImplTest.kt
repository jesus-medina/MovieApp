package com.backbase.assignment.data.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


class MovieRemoteDataSourceImplTest {
    private lateinit var format: Json

    private lateinit var movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl

    private lateinit var randomId: String

    private var staticId = "static-id"

    private lateinit var remoteDataNowPlayingResponse: String

    private lateinit var remoteDataMostPopularResponse: String

    private lateinit var remoteDataDetailedNowPlayingMovie: String

    private lateinit var remoteDataDetailedMostPopularMovie: String

    @Before
    fun setUp() {
        movieRemoteDataSourceImpl =
            MovieRemoteDataSourceImpl(mockHttpClient())

        format = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

        randomId = "${Random.nextInt()}"

        remoteDataNowPlayingResponse = """
{
  "results": [
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    }
  ]
}
""".trimIndent()

        remoteDataMostPopularResponse = """
{
  "results": [
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    },
    {
      "id": $randomId
    }
  ]
}
""".trimIndent()

        remoteDataDetailedNowPlayingMovie = """
{
  "genres": [
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 80,
      "name": "Crime"
    }
  ],
  "id": $randomId,
  "original_title": "Lock, Stock and Two Smoking Barrels",
  "overview": "A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.",
  "poster_path": "/8kSerJrhrJWKLk1LViesGcnrUPE.jpg",
  "release_date": "1998-03-05",
  "runtime": 105,
  "vote_average": 8.2
}
""".trimIndent()

        remoteDataDetailedMostPopularMovie = """
{
  "genres": [
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 80,
      "name": "Crime"
    }
  ],
  "id": $randomId,
  "original_title": "Lock, Stock and Two Smoking Barrels",
  "overview": "A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.",
  "poster_path": "/8kSerJrhrJWKLk1LViesGcnrUPE.jpg",
  "release_date": "1998-03-05",
  "runtime": 105,
  "vote_average": 8.2
}
""".trimIndent()
    }

    @Test
    fun getNowPlayingMoviesShouldReturnExpectedListOfRemoteDataNowPlayingMoviesGivenResultsOnRemoteDataNowPlayingResponseReturnsExpectedListOfRemoteDataNowPlayingMovies() =
        runBlocking {
            // Given
            val expectedListOfRemoteDataNowPlayingMovies: List<RemoteDataNowPlayingMovie> =
                (format.decodeFromString(remoteDataNowPlayingResponse) as RemoteDataNowPlayingResponse).results

            // When
            val result = movieRemoteDataSourceImpl.getNowPlayingMovies()

            // Then
            assertThat(result, `is`(expectedListOfRemoteDataNowPlayingMovies))
        }

    @Test
    fun getMostPopularMoviesShouldReturnExpectedListOfRemoteDataMostPopularMoviesGivenResultsOnRemoteDataMostPopularResponseReturnsExpectedListOfRemoteDataMostPopularMovies() =
        runBlocking {
            // Given
            val expectedListOfRemoteDataMostPopularMovies: List<RemoteDataMostPopularMovie> =
                (format.decodeFromString(remoteDataMostPopularResponse) as RemoteDataMostPopularResponse).results

            // When
            val result = movieRemoteDataSourceImpl.getMostPopularMovies()

            // Then
            assertThat(result, `is`(expectedListOfRemoteDataMostPopularMovies))
        }

    @Test
    fun getDetailedNowPlayingMovieByIdShouldReturnExpectedRemoteDataDetailedNowPlayingMovieGivenGetOnHttpClientReturnsExpectedRemoteDataDetailedNowPlayingMovie() =
        runBlocking {
            // Given
            val expectedRemoteDataDetailedNowPlayingMovie: RemoteDataDetailedNowPlayingMovie =
                format.decodeFromString(remoteDataDetailedNowPlayingMovie)

            // When
            val result = movieRemoteDataSourceImpl.getDetailedNowPlayingMovieById(staticId)

            // Then
            assertThat(result, `is`(expectedRemoteDataDetailedNowPlayingMovie))
        }

    @Test
    fun getDetailedMostPopularMovieByIdShouldReturnExpectedRemoteDataDetailedMostPopularMovieGivenGetOnHttpClientReturnsExpectedRemoteDataDetailedMostPopularMovie() =
        runBlocking {
            // Given
            val expectedRemoteDataDetailedMostPopularMovie: RemoteDataDetailedMostPopularMovie =
                format.decodeFromString(remoteDataDetailedMostPopularMovie)

            // When
            val result = movieRemoteDataSourceImpl.getDetailedMostPopularMovieById(staticId)

            // Then
            assertThat(result, `is`(expectedRemoteDataDetailedMostPopularMovie))
        }

    private fun mockHttpClient() = HttpClient(MockEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        engine {
            addHandler { request ->
                when (request.url.fullUrl) {
                    "$ENDPOINT_THE_MOVIE_DB$ENDPOINT_SECTION_NOW_PLAYING_MOVIE$API_KEY" -> {
                        val responseHeaders =
                            headersOf("Content-Type" to listOf("${ContentType.Application.Json}"))
                        respond(remoteDataNowPlayingResponse, headers = responseHeaders)
                    }
                    "$ENDPOINT_THE_MOVIE_DB$ENDPOINT_SECTION_MOST_POPULAR_MOVIE$API_KEY" -> {
                        val responseHeaders =
                            headersOf("Content-Type" to listOf("${ContentType.Application.Json}"))
                        respond(remoteDataMostPopularResponse, headers = responseHeaders)
                    }
                    "$ENDPOINT_THE_MOVIE_DB$staticId$ENDPOINT_QUERY$API_KEY" -> {
                        val responseHeaders =
                            headersOf("Content-Type" to listOf("${ContentType.Application.Json}"))
                        respond(remoteDataDetailedNowPlayingMovie, headers = responseHeaders)
                    }
                    "$ENDPOINT_THE_MOVIE_DB$staticId$ENDPOINT_QUERY$API_KEY" -> {
                        val responseHeaders =
                            headersOf("Content-Type" to listOf("${ContentType.Application.Json}"))
                        respond(remoteDataNowPlayingResponse, headers = responseHeaders)
                    }
                    else -> error("Unhandled ${request.url.fullUrl}")
                }
            }
        }
    }

    private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
    private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

    companion object {
        private const val ENDPOINT_QUERY = "?language=en-US&page=1&api_key="
        private const val ENDPOINT_THE_MOVIE_DB = "https://api.themoviedb.org/3/movie/"
        private const val ENDPOINT_SECTION_NOW_PLAYING_MOVIE = "now_playing$ENDPOINT_QUERY"
        private const val ENDPOINT_SECTION_MOST_POPULAR_MOVIE = "popular$ENDPOINT_QUERY"
        private const val API_KEY = "0ef323a42aa406b147aee0b891a5e3f9"
    }
}