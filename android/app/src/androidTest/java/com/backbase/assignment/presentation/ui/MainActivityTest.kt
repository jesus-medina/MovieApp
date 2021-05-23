package com.backbase.assignment.presentation.ui

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.platform.app.InstrumentationRegistry
import com.backbase.assignment.R
import com.backbase.assignment.matchesWithText
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import com.backbase.assignment.withRecyclerView
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
        context = InstrumentationRegistry.getInstrumentation().context
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
        val uiMostPopularMovie: UIMovie.UIMostPopularMovie = mockk {
            every { title } returns expectedTitle
        }
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
}