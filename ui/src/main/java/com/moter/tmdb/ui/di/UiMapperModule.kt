package com.moter.tmdb.ui.di

import com.moter.tmdb.domain.entity.MovieDetailEntity
import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.BaseMapper
import com.moter.tmdb.domain.mapper.ListMapper
import com.moter.tmdb.ui.detail.mapper.MovieDetailUIMapper
import com.moter.tmdb.ui.main.mapper.NowPlayingUIMapper
import com.moter.tmdb.ui.main.mapper.UpcomingUIMapper
import com.moter.tmdb.ui.model.MovieDetailUIState
import com.moter.tmdb.ui.model.NowPlayingUIState
import com.moter.tmdb.ui.model.UpcomingUIState
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
abstract class UiMapperModule {
    @Binds
    @Singleton
    abstract fun provideNowPlayingUiMapper(nowPlayingUIMapper: NowPlayingUIMapper): ListMapper<MovieListItemEntity, NowPlayingUIState>

    @Binds
    @Singleton
    abstract fun provideUpcomingUiMapper(upcomingUIMapper: UpcomingUIMapper): ListMapper<MovieListItemEntity, UpcomingUIState>

    @Binds
    @Singleton
    abstract fun provideMovieDetailUiMapper(movieDetailUIMapper: MovieDetailUIMapper): BaseMapper<MovieDetailEntity, MovieDetailUIState>
}