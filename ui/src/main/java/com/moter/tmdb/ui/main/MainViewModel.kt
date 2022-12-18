package com.moter.tmdb.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.ListMapper
import com.moter.tmdb.domain.usecase.GetNowPlayingMoviesUseCase
import com.moter.tmdb.ui.R
import com.moter.tmdb.ui.ScreenState
import com.moter.tmdb.ui.main.paging.UpcomingPagingRepository
import com.moter.tmdb.ui.model.NowPlayingUIState
import com.moter.tmdb.ui.model.UpcomingUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by muratoter on 10,December,2022
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val mapper: ListMapper<MovieListItemEntity, NowPlayingUIState>,
    upcomingPagingRepository: UpcomingPagingRepository
) : ViewModel() {

    private val _nowPlayingScreenState =
        MutableStateFlow<ScreenState<List<NowPlayingUIState>>>(value = ScreenState.Loading)
    val nowPlayingScreenState: StateFlow<ScreenState<List<NowPlayingUIState>>> get() = _nowPlayingScreenState.asStateFlow()

    private val _upcomingMoviesState =
        MutableStateFlow<ScreenState<PagingData<UpcomingUIState>>>(value = ScreenState.Loading)
    val upcomingMoviesState: StateFlow<ScreenState<PagingData<UpcomingUIState>>>
        get() = _upcomingMoviesState.asStateFlow()

    private val upcomingMovieList =
        upcomingPagingRepository.upcomingMovies().cachedIn(viewModelScope)

    init {
        getNowPlayingMovie()
        getUpcomingMovies()
    }


    fun getNowPlayingMovie() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        _nowPlayingScreenState.emit(ScreenState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        _nowPlayingScreenState.emit(ScreenState.Success(mapper.map(it.result)))
                    }
                    is NetworkResponseState.Error -> {
                        _nowPlayingScreenState.emit(ScreenState.Error(R.string.error_message))
                    }
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            upcomingMovieList.collect {
                _upcomingMoviesState.emit(ScreenState.Success(it))
            }
        }
    }

}