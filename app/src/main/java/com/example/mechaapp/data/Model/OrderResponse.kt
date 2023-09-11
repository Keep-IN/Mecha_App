package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponse(
    val status: Int,
//    val id_service: String,
    val msg: String,
    val order: OrderModel? = null
)
