package com.example.final.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ResponseHandler @Inject constructor(){
    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = call()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            emit(Resource.Error(ApiErrorStates.handleException(e).message))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}