package com.moter.tmdb.data.api

import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by muratoter on 10,December,2022
 */
interface TMDBApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int = 1,
    ): MovieListResponse

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: String): MovieItemResponse
}