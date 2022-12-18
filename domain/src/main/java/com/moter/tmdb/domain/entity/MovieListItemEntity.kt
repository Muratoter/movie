package com.moter.tmdb.domain.entity

/**
 * Created by muratoter on 10,December,2022
 */
data class MovieListItemEntity(
    val id: Int,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String?
)
