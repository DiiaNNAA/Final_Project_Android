package com.example.Final_Project.data.common

import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeoutException

sealed class ApiErrorStates(open val message: String) {
    data class ClientError(override val message: String, val code: Int) : ApiErrorStates(message)
    data class ServerError(override val message: String) : ApiErrorStates(message)
    data class HttpError(override val message: String, val httpCode: Int, val errorBody: String = "") : ApiErrorStates(message)
    data class NetworkError(override val message: String) : ApiErrorStates(message)
    data class TimeoutError(override val message: String) : ApiErrorStates(message)
    data class UnknownError(override val message: String) : ApiErrorStates(message)

    companion object {
        fun handleException(exception: Exception): ApiErrorStates {
            return when (exception) {
                is IOException -> {
                    Logger.logError(exception.stackTraceToString())
                    NetworkError("No Internet Connection")
                }
                is HttpException -> {
                    when (exception.code()) {

                        in 400..499 -> {
                            val errorModel = exception.response()?.errorBody()?.string()

                            Logger.logError(exception.stackTraceToString())
                            ClientError(errorModel!!, exception.code())
                        }

                        in 500..599 -> {
                            Logger.logError(exception.stackTraceToString())
                            ServerError("Server Error")
                        }

                        else -> {
                            Logger.logError(exception.stackTraceToString())
                            HttpError("Unknown Http Error With code: ", exception.code())
                        }
                    }
                }

                is TimeoutException -> {
                    Logger.logError(exception.stackTraceToString())
                    TimeoutError("Request Timed Out, Slow Connection")
                }

                else -> {
                    Logger.logError(exception.stackTraceToString())
                    UnknownError("Unknown error, try again later")
                }
            }
        }
    }
}

private object Logger {
    private const val TAG = "ApiCallErrorsLog"

    fun logError(error: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logMessage = "Time: $timestamp Error: $error"
        Log.e(TAG, logMessage)
    }
}
