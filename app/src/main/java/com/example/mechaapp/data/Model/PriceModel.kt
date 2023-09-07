package com.example.mechaapp.data.Model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PriceModel(
    val id: Int,
    val price: Int,
    val description_service: String
): Parcelable
