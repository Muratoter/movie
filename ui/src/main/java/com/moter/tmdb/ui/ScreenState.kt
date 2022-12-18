package com.moter.tmdb.ui

import androidx.annotation.StringRes

/**
 * Created by muratoter on 10,December,2022
 */
sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Error(@StringRes val message: Int) : ScreenState<Nothing>()
    data class Success<out T : Any>(val data: T) : ScreenState<T>()
}