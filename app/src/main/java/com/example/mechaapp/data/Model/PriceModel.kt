package com.example.mechaapp.data.Model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PriceModel(
    val price: String,
    val id_service: String = "",
    val description_service: String
): Parcelable
