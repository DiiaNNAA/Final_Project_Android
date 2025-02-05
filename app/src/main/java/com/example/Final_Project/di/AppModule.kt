package com.example.Final_Project.di

import com.example.Final_Project.data.common.ResponseHandler
import com.example.Final_Project.data.user.service.GetUserDetailsService
import com.example.Final_Project.data.users.service.GetUsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val REGRES_BASE_URL = "https://reqres.in/api/"
    private const val MOCKY_BASE_URL = "https://run.mocky.io/"

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Singleton
    @Provides
    @Named("RegresRetrofit")
    fun provideRetrofitClientForRegres(moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(REGRES_BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).build()
    }

    @Singleton
    @Provides
    @Named("MockyRetrofit")
    fun provideRetrofitClientForMocky(moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(MOCKY_BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).build()
    }

    @Singleton
    @Provides
    fun provideGetUsersService(@Named("MockyRetrofit") retrofit: Retrofit): GetUsersService {
        return retrofit.create(GetUsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailsService(@Named("RegresRetrofit") retrofit: Retrofit): GetUserDetailsService {
        return retrofit.create(GetUserDetailsService::class.java)
    }
}