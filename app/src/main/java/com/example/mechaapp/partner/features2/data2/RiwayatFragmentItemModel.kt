package com.example.mechaapp.partner.features2.data2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatFragmentItemModel (
    val id: String,
    val layanan: String,
    val pemesan: String,
    val date: String,
    val status: String
        ):Parcelable
