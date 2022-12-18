package com.moter.tmdb.data.di

import com.moter.tmdb.data.BuildConfig
import com.moter.tmdb.data.api.TMDBApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by muratoter on 10,December,2022
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(
                chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
            )
            .build()
    )

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun getHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { apiKeyAsQuery(it) }
            .build()
    }

    @Provides
    @Singleton
    fun provideTMDSApiService(
        client: OkHttpClient
    ): TMDBApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TMDBApiService::class.java)
    }
}