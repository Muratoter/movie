package com.moter.tmdb.domain.usecase

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.api.dto.movie.list.Result
import com.moter.tmdb.data.repository.MovieRepository
import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.ListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: ListMapper<Result, MovieListItemEntity>
) {
    suspend operator fun invoke(): Flow<NetworkResponseState<List<MovieListItemEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        when (val response = repository.getNowPlayingMovies()) {
            is NetworkResponseState.Error -> {
                emit(response)
            }
            is NetworkResponseState.Success -> {
                emit(NetworkResponseState.Success(mapper.map(response.result.results)))
            }
            else -> Unit
        }
    }
}