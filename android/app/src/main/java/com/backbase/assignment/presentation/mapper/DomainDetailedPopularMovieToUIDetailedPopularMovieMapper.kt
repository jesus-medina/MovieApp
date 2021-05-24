package com.backbase.assignment.presentation.mapper

import com.backbase.assignment.domain.DomainMovie
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.mapper.Mapper
import javax.inject.Inject

interface DomainDetailedPopularMovieToUIDetailedPopularMovieMapper :
    Mapper<DomainMovie.DomainDetailedPopularMovie, UIMovie.UIDetailedPopularMovie>

class DomainDetailedPopularMovieToUIDetailedPopularMovieMapperImpl @Inject constructor() :
    DomainDetailedPopularMovieToUIDetailedPopularMovieMapper {
    override fun map(input: DomainMovie.DomainDetailedPopularMovie): UIMovie.UIDetailedPopularMovie {
        TODO("Not yet implemented")
    }
}