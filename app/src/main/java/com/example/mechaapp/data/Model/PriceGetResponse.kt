package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceGetResponse(
    val status: Int,
    val msg: String,
    val price: List<PriceModel> = emptyList()
)
