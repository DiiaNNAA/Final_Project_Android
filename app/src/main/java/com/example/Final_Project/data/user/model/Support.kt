package com.example.Final_Project.data.user.model

import com.squareup.moshi.Json

data class Support(
    @Json(name = "text")
    val text: String,
    @Json(name = "url")
    val url: String
)