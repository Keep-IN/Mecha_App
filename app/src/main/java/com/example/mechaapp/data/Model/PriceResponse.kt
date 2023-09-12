package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceResponse(
    val msg: String,
    val price: PriceModel? = null
)
