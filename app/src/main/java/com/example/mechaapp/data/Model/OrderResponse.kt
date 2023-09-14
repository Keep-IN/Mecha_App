package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponse(
    val status: Int = 0,
    val id: Int = 0,
    val msg: String = "",
    val order: OrderModel? = null
)
