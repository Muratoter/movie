package com.moter.tmdb.data.di

import com.moter.tmdb.data.source.RemoteDataSource
import com.moter.tmdb.data.source.RemoteDataSourceImpl
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
abstract class SourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}