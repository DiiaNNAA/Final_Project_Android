package com.example.Final_Project.data.users.service

import com.example.Final_Project.data.users.model.GetUsersResponseDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface GetUsersService {
    @GET("https://run.mocky.io/v3/986b3c02-8050-481b-9850-3ac741030c89")
    suspend fun getUsersList(): Response<List<GetUsersResponseDtoItem>>
}