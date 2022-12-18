package com.moter.tmdb.data.repository

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse
import com.moter.tmdb.data.di.IoDispatcher
import com.moter.tmdb.data.source.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    MovieRepository {
    override suspend fun getSingleMovie(id: String): NetworkResponseState<MovieItemResponse> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getSingleMovie(id)
            } catch (e: Exception) {
                NetworkResponseState.Error(e)
            }
        }

    override suspend fun getNowPlayingMovies(): NetworkResponseState<MovieListResponse> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getNowPlayingMovies()
            } catch (e: Exception) {
                NetworkResponseState.Error(e)
            }
        }

    override suspend fun getUpcomingMovies(page: Int): NetworkResponseState<MovieListResponse> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getUpcomingMovies(page = page)
            } catch (e: Exception) {
                NetworkResponseState.Error(e)
            }
        }

}