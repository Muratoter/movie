package com.moter.tmdb.domain.mapper

/**
 * Created by muratoter on 10,December,2022
 */
interface BaseMapper<I, O> {
    fun map(input: I): O
}