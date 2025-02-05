package com.example.Final_Project.domain.user_details

import com.example.Final_Project.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface IGetUserDetailsRepository {
    suspend fun getUserDetails(userId: Int) : Flow<Resource<GetUserDetailsResponse>>
}