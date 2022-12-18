package com.moter.tmdb.ui.model

/**
 * Created by muratoter on 10,December,2022
 */
data class UpcomingUIState(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String?,
    val releaseDate: String
)
