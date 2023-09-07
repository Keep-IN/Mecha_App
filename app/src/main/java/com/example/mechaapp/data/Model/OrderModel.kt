package com.example.mechaapp.data.Model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class OrderModel(
    val id: Int,
    val name_services: String,
    val status: String?,
    val address: String,
    val map_url: String,
    val created_at: String,
    val sum: String?,
    val price: List<PriceModel> = mutableListOf()
):Parcelable
