package com.example.Final_Project.domain.users_list

import com.example.Final_Project.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface IGetUsersRepository {
    suspend fun getUsers(): Flow<Resource<List<GetUsersResponseModelItem>>>
}