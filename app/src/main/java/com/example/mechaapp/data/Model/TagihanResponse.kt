package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagihanResponse(
    val status: Int,
    val msg: String,
    val updatedTagihan: String
)
