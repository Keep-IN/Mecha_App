package com.example.mechaapp.partner.data2

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class RiwayatItemModel2 (
    val judul: String,
    val isistatus: String,
    val tanggalorderan: String,
    val hargaorderan: String
    ) :Parcelable