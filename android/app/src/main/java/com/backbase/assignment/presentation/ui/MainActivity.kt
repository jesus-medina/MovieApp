package com.backbase.assignment.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.ActivityMainBinding
import com.backbase.assignment.presentation.MovieDetailsActivity
import com.backbase.assignment.presentation.ui.adapter.MoviesAdapter
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import com.backbase.assignment.util.startActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var releaseDateFormat: DateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesAdapter = MoviesAdapter(releaseDateFormat) {
            startActivity<MovieDetailsActivity>()
        }

        binding.mostPopularMoviesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter
        }

        lifecycleScope.launchWhenCreated {
            movieViewModel.retrieveMovies()
            movieViewModel.getMostPopularMovies().collect {
                moviesAdapter.submitList(it)
            }
        }
    }
}
