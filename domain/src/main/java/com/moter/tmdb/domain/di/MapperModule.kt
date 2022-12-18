package com.moter.tmdb.domain.di

import com.moter.tmdb.data.api.dto.movie.item.MovieItemResponse
import com.moter.tmdb.data.api.dto.movie.list.MovieListResponse
import com.moter.tmdb.data.api.dto.movie.list.Result
import com.moter.tmdb.domain.entity.MovieDetailEntity
import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.entity.UpcomingEntity
import com.moter.tmdb.domain.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by muratoter on 10,December,2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun provideSingleMovieMapper(singleMovieEntityMapper: SingleMovieEntityMapper): BaseMapper<MovieItemResponse, MovieDetailEntity>

    @Binds
    @Singleton
    abstract fun provideNowPlayingMovieMapper(movieListEntityMapper: MovieListEntityMapper): ListMapper<Result, MovieListItemEntity>

    @Binds
    @Singleton
    abstract fun provideUpcomingMovieMapper(upcomingEntityMapper: UpcomingEntityMapper): BaseMapper<MovieListResponse, UpcomingEntity>

}