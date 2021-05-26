package com.backbase.assignment.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.assignment.*
import com.backbase.assignment.presentation.UIGenre
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.ui.custom.RatingView
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@HiltAndroidTest
class MovieDetailsActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    var movieViewModel: MovieViewModel = mockk(relaxed = true) {
        coEvery { retrieveMovies() } just runs
    }

    lateinit var context: Context

    @Before
    fun setUp() {
        hiltRule.inject()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        Fresco.initialize(context)
    }

    @Test
    fun titleTextViewShouldMatchesWithExpectedTitleGivenGetMovieByIdOnMovieViewModelReturnsExpectedTitle() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val expectedTitle = "${Random.nextInt()}"
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                title = expectedTitle
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        onView(withId(R.id.titleTextView)).matchesWithText(expectedTitle)
    }

    @Test
    fun subtitleTextViewShouldStartsWithExpectedFormattedReleasedDateGivenGetMovieByIdOnMovieViewModelReturnsExpectedFormattedReleasedDate() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val releaseDate = Date(Random.nextLong())
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                releaseDate = releaseDate
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        val releaseDateFormat = SimpleDateFormat("MMMM d, y")
        val expectedFormattedReleasedDate = releaseDateFormat.format(releaseDate)
        onView(withId(R.id.subtitleTextView)).startsWithText(expectedFormattedReleasedDate)
    }

    @Test
    fun subtitleTextViewShouldEndsWithExpectedDurationGivenGetMovieByIdOnMovieViewModelReturnsExpectedDuration() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val expectedDuration = "${Random.nextInt()}"
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                duration = expectedDuration
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        onView(withId(R.id.subtitleTextView)).endsWithText(expectedDuration)
    }

    @Test
    fun subtitleTextViewShouldContainsExpectedFormattedGenresListGivenGetMovieByIdOnMovieViewModelReturnsExpectedFormattedGenresList() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val genres = listOf(
            UIGenre.Action,
            UIGenre.Adventure,
            UIGenre.Animation,
            UIGenre.Comedy,
            UIGenre.Crime,
            UIGenre.Documentary,
            UIGenre.Drama,
            UIGenre.Family,
            UIGenre.Fantasy,
            UIGenre.History,
            UIGenre.Horror,
            UIGenre.Music,
            UIGenre.Mystery,
            UIGenre.Romance,
            UIGenre.ScienceFiction,
            UIGenre.TVMovie,
            UIGenre.Thriller,
            UIGenre.War,
            UIGenre.Western,
        )
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                genres = genres
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        val expectedFormattedGenresList = genres.formattedGenres()
        onView(withId(R.id.subtitleTextView)).containsText(expectedFormattedGenresList)
    }

    @Test
    fun ratingShouldBeExpectedRatingGivenGetMovieByIdOnMovieViewModelReturnsExpectedRating() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val expectedRating = Random.nextInt(0, 100)
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                rating = expectedRating
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        onView(withId(R.id.ratingView)).check { view, _ ->
            view as RatingView
            assertThat(view.rating, `is`(expectedRating))
        }
    }

    @Test
    fun overviewTextViewShouldMatchesWithExpectedOverviewGivenGetMovieByIdOnMovieViewModelReturnsExpectedOverview() {
        // Given
        val movieId = "${Random.nextInt()}"
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        val expectedOverview = "${Random.nextInt()}"
        coEvery { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                overview = expectedOverview
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        onView(withId(R.id.overviewTextView)).matchesWithText(expectedOverview)
    }

    private fun createUIDetailedPopularMovie(
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(0, 100),
        releaseDate: Date = Date(Random.nextLong()),
        duration: String = "${Random.nextInt()}",
        overview: String = "${Random.nextInt()}",
        genres: List<UIGenre> = listOf(
            UIGenre.Action,
            UIGenre.Adventure,
            UIGenre.Animation,
            UIGenre.Comedy,
            UIGenre.Crime,
            UIGenre.Documentary,
            UIGenre.Drama,
            UIGenre.Family,
            UIGenre.Fantasy,
            UIGenre.History,
            UIGenre.Horror,
            UIGenre.Music,
            UIGenre.Mystery,
            UIGenre.Romance,
            UIGenre.ScienceFiction,
            UIGenre.TVMovie,
            UIGenre.Thriller,
            UIGenre.War,
            UIGenre.Western,
        ),
    ): UIMovie.UIDetailedMovie =
        UIMovie.UIDetailedMovie("", "", title, rating, duration, releaseDate, overview, genres)

    private fun List<UIGenre>.formattedGenres(): String = joinToString(", ", transform = {
        context.getString(
            when (it) {
                UIGenre.Action -> R.string.genre_action
                UIGenre.Adventure -> R.string.genre_adventure
                UIGenre.Animation -> R.string.genre_animation
                UIGenre.Comedy -> R.string.genre_comedy
                UIGenre.Crime -> R.string.genre_crime
                UIGenre.Documentary -> R.string.genre_documentary
                UIGenre.Drama -> R.string.genre_drama
                UIGenre.Family -> R.string.genre_family
                UIGenre.Fantasy -> R.string.genre_fantasy
                UIGenre.History -> R.string.genre_history
                UIGenre.Horror -> R.string.genre_horror
                UIGenre.Music -> R.string.genre_music
                UIGenre.Mystery -> R.string.genre_mystery
                UIGenre.Romance -> R.string.genre_romance
                UIGenre.ScienceFiction -> R.string.genre_science_fiction
                UIGenre.TVMovie -> R.string.genre_tv_movie
                UIGenre.Thriller -> R.string.genre_thriller
                UIGenre.War -> R.string.genre_war
                UIGenre.Western -> R.string.genre_western
            }
        )
    })
}
