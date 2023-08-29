package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true )
data class RegisResponse(
    val status: Int,
    val message: String,
    val verified: Boolean
)
