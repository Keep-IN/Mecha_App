package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderGetResponse (
    val status: Int,
    val msg: String,
    val order: List<OrderModel> = mutableListOf()
)