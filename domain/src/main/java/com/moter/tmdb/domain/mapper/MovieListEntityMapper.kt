package com.moter.tmdb.domain.mapper

import com.moter.tmdb.data.api.dto.movie.list.Result
import com.moter.tmdb.domain.entity.MovieListItemEntity
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class MovieListEntityMapper @Inject constructor() : ListMapper<Result, MovieListItemEntity> {
    override fun map(input: List<Result>): List<MovieListItemEntity> {
        return input.map { result ->
            MovieListItemEntity(
                id = result.id,
                original_title = result.originalTitle,
                overview = result.overview,
                release_date = result.releaseDate,
                poster_path = result.posterPath
            )
        }
    }
}