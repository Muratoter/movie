package com.moter.tmdb.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.domain.entity.MovieDetailEntity
import com.moter.tmdb.domain.mapper.BaseMapper
import com.moter.tmdb.domain.usecase.GetSingleMovieUseCase
import com.moter.tmdb.ui.R
import com.moter.tmdb.ui.ScreenState
import com.moter.tmdb.ui.model.MovieDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by muratoter on 11,December,2022
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSingleMovieUseCase: GetSingleMovieUseCase,
    state: SavedStateHandle,
    private val mapper: BaseMapper<MovieDetailEntity, MovieDetailUIState>
) : ViewModel() {
    init {
        val movieId = state.get<Int>("movieId")
        movieId?.let {
            getSingleMovie(it.toString())
        }
    }

    private val _movieDetailScreenState =
        MutableStateFlow<ScreenState<MovieDetailUIState>>(value = ScreenState.Loading)
    val movieDetailScreenState: StateFlow<ScreenState<MovieDetailUIState>>
        get() = _movieDetailScreenState.asStateFlow()

    fun getSingleMovie(movieId: String) {
        viewModelScope.launch {
            getSingleMovieUseCase(id = movieId).collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        Timber.d("Loading")
                        _movieDetailScreenState.emit(ScreenState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        Timber.d("Success ${it.result}")
                        _movieDetailScreenState.emit(ScreenState.Success(mapper.map(it.result)))
                    }
                    is NetworkResponseState.Error -> {
                        Timber.d("Error ")
                        _movieDetailScreenState.emit(ScreenState.Error(R.string.error_message))
                    }
                }
            }
        }
    }
}