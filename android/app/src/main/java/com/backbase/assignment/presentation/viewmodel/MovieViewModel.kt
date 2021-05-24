package com.backbase.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.domain.usecase.GetMostPopularMoviesUseCase
import com.backbase.assignment.domain.usecase.GetMovieByIdUseCase
import com.backbase.assignment.domain.usecase.GetNowPlayingMoviesUseCase
import com.backbase.assignment.domain.usecase.RetrieveMoviesUseCase
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.presentation.mapper.DomainDetailedPopularMovieToUIDetailedPopularMovieMapper
import com.backbase.assignment.utils.mapper.ListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val retrieveMoviesUseCase: RetrieveMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val domainNowPlayingMovieToUINowPlayingMovieListMapper: ListMapper<DomainMovie.DomainNowPlayingMovie, UIMovie.UINowPlayingMovie>,
    private val getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val domainMostPopularMoviesToUIMostPopularMovieListMapper: ListMapper<DomainMovie.DomainMostPopularMovie, UIMovie.UIMostPopularMovie>,
    private var getMovieByIdUseCase: GetMovieByIdUseCase,
    private var domainDetailedPopularMovieToUIDetailedPopularMovie: DomainDetailedPopularMovieToUIDetailedPopularMovieMapper
) : ViewModel() {
    suspend fun retrieveMovies() {
        retrieveMoviesUseCase()
    }

    suspend fun getNowPlayingMovies(): StateFlow<List<UIMovie.UINowPlayingMovie>> =
        getNowPlayingMoviesUseCase()
            .map(domainNowPlayingMovieToUINowPlayingMovieListMapper::map)
            .stateIn(viewModelScope)

    suspend fun getMostPopularMovies(): StateFlow<List<UIMovie.UIMostPopularMovie>> =
        getMostPopularMoviesUseCase()
            .map(domainMostPopularMoviesToUIMostPopularMovieListMapper::map)
            .stateIn(viewModelScope)

    fun getMovieById(id: String): UIMovie.UIDetailedPopularMovie {
        val domainDetailedPopularMovie = getMovieByIdUseCase(id)

        return domainDetailedPopularMovieToUIDetailedPopularMovie.map(domainDetailedPopularMovie)
    }

}