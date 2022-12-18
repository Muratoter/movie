package com.moter.tmdb.ui.main.mapper

import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.ListMapper
import com.moter.tmdb.ui.extensions.formatDate
import com.moter.tmdb.ui.model.UpcomingUIState
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class UpcomingUIMapper @Inject constructor() : ListMapper<MovieListItemEntity, UpcomingUIState> {
    override fun map(input: List<MovieListItemEntity>): List<UpcomingUIState> {
        return input.map {
            UpcomingUIState(
                id = it.id,
                title = it.original_title,
                overview = it.overview,
                poster = it.poster_path,
                releaseDate = it.release_date.formatDate()
            )
        }
    }
}