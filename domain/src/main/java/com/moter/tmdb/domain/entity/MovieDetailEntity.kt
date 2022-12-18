package com.moter.tmdb.domain.entity

/**
 * Created by muratoter on 10,December,2022
 */
data class MovieDetailEntity(
    val id: Int,
    val imdb_id: String?,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Double,
    val poster_path: String?
)
