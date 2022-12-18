package com.moter.tmdb.ui.model

/**
 * Created by muratoter on 11,December,2022
 */
data class MovieDetailUIState(
    val title: String,
    val overview: String,
    val release: String,
    val voteAverage: String,
    val poster:String?
)
