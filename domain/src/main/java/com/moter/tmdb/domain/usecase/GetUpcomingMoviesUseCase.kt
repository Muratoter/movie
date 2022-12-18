package com.moter.tmdb.domain.usecase

import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.data.repository.MovieRepository
import com.moter.tmdb.domain.entity.UpcomingEntity
import com.moter.tmdb.domain.mapper.UpcomingEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: UpcomingEntityMapper
) {
    suspend operator fun invoke(page: Int): Flow<NetworkResponseState<UpcomingEntity>> = flow {
        emit(NetworkResponseState.Loading)
        when (val response = repository.getUpcomingMovies(page)) {
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