package com.amrk000.yajhaz.di.module

import com.amrk000.yajhaz.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private val BASE_URL = BuildConfig.BASE_URL //add api url in local.properties as BASE_URL

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(gson: GsonConverterFactory?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gson)
            .build()
    }
}