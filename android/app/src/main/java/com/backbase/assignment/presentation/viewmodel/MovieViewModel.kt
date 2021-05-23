package com.backbase.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.assignment.presentation.UIMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor() : ViewModel() {
    suspend fun retrieveMovies() {
        TODO("retrieveMovies is not implemented yet")
    }

    fun getNowPlayingMovies(): StateFlow<List<UIMovie.UINowPlayingMovie>> {
        TODO("getNowPlayingMovies is not implemented yet")
    }

    fun getMostPopularMovies(): StateFlow<List<UIMovie.UIMostPopularMovie>> {
        TODO("getMostPopularMovies is not implemented yet")
    }

    fun getMovieById(id: String): UIMovie.UIDetailedPopularMovie {
        TODO("getMovieById is not implemented yet")
    }

}