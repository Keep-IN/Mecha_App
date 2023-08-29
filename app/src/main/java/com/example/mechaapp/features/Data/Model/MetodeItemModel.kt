package com.example.mechaapp.features.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetodeItemModel(
    val namabank: String,
    val img: String
): Parcelable
