package com.example.Final_Project.data.common

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(loading: Boolean) : Resource<T>(loading = loading)
    class Pending<T> : Resource<T>()
    data class Error<T>(val errMessage: String) : Resource<T>(errorMessage = errMessage)
}
