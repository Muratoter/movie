package com.moter.tmdb.data.di

import com.moter.tmdb.data.repository.MovieRepository
import com.moter.tmdb.data.repository.MovieRepositoryImpl
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
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}