package com.moter.tmdb.ui.detail.mapper

import com.moter.tmdb.domain.entity.MovieDetailEntity
import com.moter.tmdb.domain.mapper.BaseMapper
import com.moter.tmdb.ui.extensions.decimalFormat
import com.moter.tmdb.ui.extensions.formatDate
import com.moter.tmdb.ui.model.MovieDetailUIState
import javax.inject.Inject

/**
 * Created by muratoter on 11,December,2022
 */
class MovieDetailUIMapper @Inject constructor() :
    BaseMapper<MovieDetailEntity, MovieDetailUIState> {
    override fun map(input: MovieDetailEntity): MovieDetailUIState {
        return MovieDetailUIState(
            title = input.original_title,
            overview = input.overview,
            release = input.release_date.formatDate(),
            voteAverage = input.vote_average.decimalFormat(),
            poster = input.poster_path
        )
    }
}