package com.backbase.assignment.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.backbase.assignment.databinding.ActivityMovieDetailsBinding
import com.backbase.assignment.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("movie_id")?.let(::loadMovieDetailsBy)
    }

    private fun loadMovieDetailsBy(id: String) {
        lifecycleScope.launchWhenCreated {
            movieViewModel.getMovieById(id).collect {
                binding.titleTextView.text = it.title
            }
        }
    }
}