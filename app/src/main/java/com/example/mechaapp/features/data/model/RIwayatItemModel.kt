package com.example.mechaapp.features.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RiwayatItemModel (
    val id: String,
    val title: String,
    val pemesan: String,
    val date: String,
    val status: String
        ):Parcelable

