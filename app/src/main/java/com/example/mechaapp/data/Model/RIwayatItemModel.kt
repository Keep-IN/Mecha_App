package com.example.mechaapp.data.Model

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

