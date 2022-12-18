package com.moter.tmdb.domain.mapper

import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse
import com.moter.tmdb.domain.entity.UpcomingEntity
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class UpcomingEntityMapper @Inject constructor(private val movieListEntityMapper: MovieListEntityMapper) :
    BaseMapper<MovieListResponse, UpcomingEntity> {
    override fun map(input: MovieListResponse): UpcomingEntity {
        return UpcomingEntity(
            page = input.page,
            results = movieListEntityMapper.map(input.results),
            totalPages = input.totalPages,
            totalResults = input.totalResults
        )
    }

}