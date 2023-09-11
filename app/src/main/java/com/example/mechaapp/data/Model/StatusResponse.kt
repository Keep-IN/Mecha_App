package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusResponse (
    val status: Int,
    val msg: String
)