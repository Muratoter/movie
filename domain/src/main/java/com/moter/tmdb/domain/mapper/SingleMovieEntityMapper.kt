package com.moter.tmdb.domain.mapper

import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.domain.entity.MovieDetailEntity
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class SingleMovieEntityMapper @Inject constructor() :
    BaseMapper<MovieItemResponse, MovieDetailEntity> {
    override fun map(input: MovieItemResponse): MovieDetailEntity {
        return MovieDetailEntity(
            id = input.id,
            imdb_id = input.imdbId,
            original_title = input.originalTitle,
            overview = input.overview,
            release_date = input.releaseDate,
            vote_average = input.voteAverage,
            poster_path = input.posterPath
        )
    }
}