package com.backbase.assignment.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMovieDetailsBinding
import com.backbase.assignment.presentation.UIGenre
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.DateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    private val movieViewModel: MovieViewModel by viewModels()

    @Inject
    lateinit var releaseDateFormat: DateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("movie_id")?.let(::loadMovieDetailsBy)
    }

    private fun loadMovieDetailsBy(id: String) {
        lifecycleScope.launchWhenCreated {
            movieViewModel.getMovieById(id)
                .collect { binding.bindMovieDetails(it) }
        }
    }

    private fun ActivityMovieDetailsBinding.bindMovieDetails(uiDetailedPopularMovie: UIMovie.UIDetailedPopularMovie) {
        with(uiDetailedPopularMovie) {
            posterSimpleDraweeView.setImageURI(posterImage)
            titleTextView.text = title
            subtitleTextView.text = getString(R.string.movie_details_subtitle).format(
                releaseDateFormat.format(releaseDate), genres.formattedGenres(), duration
            )
            ratingView.rating = rating
            overviewTextView.text = overview
        }
    }

    private fun List<UIGenre>.formattedGenres(): String = joinToString(", ", transform = {
        getString(
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