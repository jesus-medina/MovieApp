package com.backbase.assignment.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.ActivityMainBinding
import com.backbase.assignment.presentation.ui.adapter.MostPopularMoviesAdapter
import com.backbase.assignment.presentation.ui.adapter.NowPlayingMoviesAdapter
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.DateFormat
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var mostPopularMostPopularMoviesAdapter: MostPopularMoviesAdapter

    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter

    @Inject
    @Named("uiReleaseDateFormat")
    lateinit var releaseDateFormat: DateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            initMostPopularMoviesRecyclerView()
            initNowPlayingMoviesListView()
        }

        lifecycleScope.launch {
            movieViewModel.retrieveMovies()
        }
        lifecycleScope.launch {
             movieViewModel.getMostPopularMovies().collect {
                mostPopularMostPopularMoviesAdapter.submitList(it)
            }
        }
        lifecycleScope.launch {
            movieViewModel.getNowPlayingMovies().collect {
                nowPlayingMoviesAdapter.submitList(it)
            }
        }
    }

    private fun ActivityMainBinding.initNowPlayingMoviesListView() {
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter()

        nowPlayingMoviesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingMoviesAdapter
        }
    }

    private fun ActivityMainBinding.initMostPopularMoviesRecyclerView() {
        mostPopularMostPopularMoviesAdapter = MostPopularMoviesAdapter(releaseDateFormat) {
            val options = Bundle().apply {
                putString("movie_id", it.id)
            }
            startActivity<MovieDetailsActivity>(options)
        }

        mostPopularMoviesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mostPopularMostPopularMoviesAdapter
        }
    }

    private inline fun <reified T : Activity> Activity.startActivity(options: Bundle?) {
        val startMovieDetailsActivityIntent = Intent(this, T::class.java).apply {
            options?.let {
                putExtras(it)
            }
        }
        startActivity(startMovieDetailsActivityIntent)
    }
}
