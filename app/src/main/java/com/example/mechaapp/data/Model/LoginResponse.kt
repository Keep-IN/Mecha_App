package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val status: Int,
    val token: String,
    val role: Int,
)