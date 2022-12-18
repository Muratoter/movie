package com.moter.tmdb.domain.entity

/**
 * Created by muratoter on 10,December,2022
 */
data class UpcomingEntity(
    val page: Int,
    val results: List<MovieListItemEntity>,
    val totalPages: Int,
    val totalResults: Int
)
