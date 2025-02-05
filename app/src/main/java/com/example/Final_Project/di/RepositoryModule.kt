package com.example.final.di

import com.example.Final_Project.data.common.ResponseHandler
import com.example.Final_Project.data.user.repository.GetUserDetailsRepositoryImpl
import com.example.Final_Project.data.user.service.GetUserDetailsService
import com.example.Final_Project.data.users.repository.GetUsersRepositoryImpl
import com.example.Final_Project.data.users.service.GetUsersService
import com.example.Final_Project.domain.user_details.IGetUserDetailsRepository
import com.example.final.domain.users_list.IGetUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGetUsersListRepository(getUsersService: GetUsersService, handler: ResponseHandler) : IGetUsersRepository {
        return GetUsersRepositoryImpl(getUsersService, handler)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailsRepository(getUserDetailsService: GetUserDetailsService, handler: ResponseHandler) : IGetUserDetailsRepository {
        return GetUserDetailsRepositoryImpl(getUserDetailsService, handler)
    }
}