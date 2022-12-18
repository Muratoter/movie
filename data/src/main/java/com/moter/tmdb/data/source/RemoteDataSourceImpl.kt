package com.moter.tmdb.data.source

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.api.TMDBApiService
import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class RemoteDataSourceImpl @Inject constructor(private val api: TMDBApiService) : RemoteDataSource {
    override suspend fun getSingleMovie(id: String): NetworkResponseState<MovieItemResponse> {
        return try {
            val response = api.getMovie(id)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getNowPlayingMovies(): NetworkResponseState<MovieListResponse> {
        return try {
            val response = api.getNowPlaying()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getUpcomingMovies(page: Int): NetworkResponseState<MovieListResponse> {
        return try {
            val response = api.getUpcoming(page = page)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }
}