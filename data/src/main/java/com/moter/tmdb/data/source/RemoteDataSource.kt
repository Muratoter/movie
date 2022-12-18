package com.moter.tmdb.data.source

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse

/**
 * Created by muratoter on 10,December,2022
 */
interface RemoteDataSource {
    suspend fun getSingleMovie(id: String): NetworkResponseState<MovieItemResponse>
    suspend fun getNowPlayingMovies(): NetworkResponseState<MovieListResponse>
    suspend fun getUpcomingMovies(page: Int): NetworkResponseState<MovieListResponse>
}