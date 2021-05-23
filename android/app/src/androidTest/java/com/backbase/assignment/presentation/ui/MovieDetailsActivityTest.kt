package com.backbase.assignment.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.assignment.R
import com.backbase.assignment.matchesWithText
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date
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
        every { movieViewModel.getMovieById(movieId) } returns MutableStateFlow(
            createUIDetailedPopularMovie(
                title = expectedTitle
            )
        )

        // When
        launchActivity<MovieDetailsActivity>(intent)

        // Then
        onView(withId(R.id.titleTextView)).matchesWithText(expectedTitle)
    }

    private fun createUIDetailedPopularMovie(
        title: String = "${Random.nextInt()}"
    ) = UIMovie.UIDetailedPopularMovie("", "", title, 0, "", Date(), "", emptyList())
}