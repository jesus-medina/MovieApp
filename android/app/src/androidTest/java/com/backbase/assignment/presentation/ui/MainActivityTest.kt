package com.backbase.assignment.presentation.ui

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.assignment.R
import com.backbase.assignment.isDisplayed
import com.backbase.assignment.endsWithText
import com.backbase.assignment.startsWithText
import com.backbase.assignment.matchesWithText
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.ui.custom.RatingView
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import com.backbase.assignment.withRecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

@HiltAndroidTest
class MainActivityTest {

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
    fun launchActivityShouldCallRetrieveMoviesOnMovieViewModel() {
        // When
        launchActivity<MainActivity>()

        // Then
        coVerify { movieViewModel.retrieveMovies() }
    }

    @Test
    fun titleTextViewOnMostPopularMoviesRecyclerViewAtFirstPositionShouldMatchesWithExpectedTitleGivenGetMostPopularMoviesOnMovieViewModelReturnsAListOfMostPopularMoviesWithExpectedTitle() {
        // Given
        val expectedTitle = "${Random.nextInt()}"
        val uiMostPopularMovie = createUIMostPopularMovie(title = expectedTitle)
        every { movieViewModel.getMostPopularMovies() } returns MutableStateFlow(
            listOf(
                uiMostPopularMovie
            )
        )

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.mostPopularMoviesRecyclerView).atPositionOnView(
                0,
                R.id.titleTextView
            )
        ).matchesWithText(expectedTitle)
    }

    @Test
    fun subtitleTextViewOnMostPopularMoviesRecyclerViewAtFirstPositionShouldMatchesWithExpectedReleaseDateGivenGetMostPopularMoviesOnMovieViewModelReturnsAListOfMostPopularMoviesWithExpectedReleaseDate() {
        // Given
        val releaseDate = Date(Random.nextLong())
        val uiMostPopularMovie = createUIMostPopularMovie(releaseDate = releaseDate)
        every { movieViewModel.getMostPopularMovies() } returns MutableStateFlow(
            listOf(
                uiMostPopularMovie
            )
        )

        // When
        launchActivity<MainActivity>()

        // Then
        val releaseDateFormat = SimpleDateFormat("MMMM d, y")
        val expectedReleaseDate = releaseDateFormat.format(releaseDate)
        onView(
            withRecyclerView(R.id.mostPopularMoviesRecyclerView).atPositionOnView(
                0,
                R.id.subtitleTextView
            )
        ).startsWithText(expectedReleaseDate)
    }

    @Test
    fun subtitleTextViewOnMostPopularMoviesRecyclerViewAtFirstPositionShouldMatchesWithExpectedDurationGivenGetMostPopularMoviesOnMovieViewModelReturnsAListOfMostPopularMoviesWithExpectedDuration() {
        // Given
        val expectedDuration = "${Random.nextInt()}"
        val uiMostPopularMovie = createUIMostPopularMovie(duration = expectedDuration)
        every { movieViewModel.getMostPopularMovies() } returns MutableStateFlow(
            listOf(
                uiMostPopularMovie
            )
        )

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.mostPopularMoviesRecyclerView).atPositionOnView(
                0,
                R.id.subtitleTextView
            )
        ).endsWithText(expectedDuration)
    }

    @Test
    fun ratingViewOnMostPopularMoviesRecyclerViewAtFirstPositionShouldMatchesWithExpectedRatingGivenGetMostPopularMoviesOnMovieViewModelReturnsAListOfMostPopularMoviesWithExpectedRating() {
        // Given
        val expectedRating = Random.nextInt(0, 100)
        val uiMostPopularMovie = createUIMostPopularMovie(rating = expectedRating)
        every { movieViewModel.getMostPopularMovies() } returns MutableStateFlow(
            listOf(
                uiMostPopularMovie
            )
        )

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.mostPopularMoviesRecyclerView).atPositionOnView(
                0,
                R.id.ratingView
            )
        ).check { view, _ ->
            view as RatingView
            assertThat(view.rating, `is`(expectedRating))
        }
    }

    @Test
    fun clickOnMostPopularMoviesRecyclerViewAtFirstPositionShouldNavigateToMovieDetailsActivity() {
        // Given
        every { movieViewModel.getMostPopularMovies() } returns MutableStateFlow(
            listOf(
                createUIMostPopularMovie(title = "")
            )
        )
        launchActivity<MainActivity>()

        // When
        onView(withRecyclerView(R.id.mostPopularMoviesRecyclerView).atPosition(0)).perform(click())

        // Then
        onView(allOf(withId(R.id.titleTextView), isDescendantOfA(withId(R.id.movieDetailsActivity)))).isDisplayed()
    }

    private fun createUIMostPopularMovie(
        title: String = "${Random.nextInt()}",
        rating: Int = Random.nextInt(0, 100),
        duration: String = "${Random.nextInt()}",
        releaseDate: Date = Date(Random.nextLong())
    ) = UIMovie.UIMostPopularMovie("", "", title, rating, duration, releaseDate)
}