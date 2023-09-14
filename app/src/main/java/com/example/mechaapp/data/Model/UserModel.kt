package com.example.mechaapp.data.Model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserModel (
    val id: Int,
    val name: String,
    val no_telp: String,
    val email: String,
    val order: List<OrderModel> = listOf(),
    val history: List<OrderModel> = listOf()
): Parcelable