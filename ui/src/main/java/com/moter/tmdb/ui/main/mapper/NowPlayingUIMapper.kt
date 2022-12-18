package com.moter.tmdb.ui.main.mapper

import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.ListMapper
import com.moter.tmdb.ui.extensions.getReleaseYear
import com.moter.tmdb.ui.model.NowPlayingUIState
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class NowPlayingUIMapper @Inject constructor() :
    ListMapper<MovieListItemEntity, NowPlayingUIState> {
    override fun map(input: List<MovieListItemEntity>): List<NowPlayingUIState> {
        return input.map {
            NowPlayingUIState(
                id = it.id,
                title = it.original_title + " (${it.release_date.getReleaseYear()})",
                overview = it.overview,
                poster = it.poster_path
            )
        }
    }
}