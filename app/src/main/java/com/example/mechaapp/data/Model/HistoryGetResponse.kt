package com.example.mechaapp.data.Model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoryGetResponse(
    val status: Int,
    val history: List<OrderModel> = mutableListOf()
)
