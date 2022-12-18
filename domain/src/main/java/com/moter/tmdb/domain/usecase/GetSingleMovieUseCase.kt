package com.moter.tmdb.domain.usecase

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.repository.MovieRepository
import com.moter.tmdb.domain.entity.MovieDetailEntity
import com.moter.tmdb.domain.mapper.SingleMovieEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class GetSingleMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: SingleMovieEntityMapper
) {

    suspend operator fun invoke(id: String): Flow<NetworkResponseState<MovieDetailEntity>> = flow {
        emit(NetworkResponseState.Loading)
        when (val response = repository.getSingleMovie(id)) {
            is NetworkResponseState.Error -> {
                emit(response)
            }
            is NetworkResponseState.Success -> {
                emit(NetworkResponseState.Success(mapper.map(response.result)))
            }
            else -> Unit
        }
    }
}