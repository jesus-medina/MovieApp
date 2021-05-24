package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface DomainMostPopularMovieToUIMostPopularMovieMapper :
    Mapper<DomainMovie.DomainMostPopularMovie, UIMovie.UIMostPopularMovie>

class DomainMostPopularMovieToUIMostPopularMovieMapperImpl @Inject constructor() : DomainMostPopularMovieToUIMostPopularMovieMapper {
    override fun map(input: DomainMovie.DomainMostPopularMovie): UIMovie.UIMostPopularMovie {
        TODO("Not yet implemented")
    }
}