package com.example.mechaapp.data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatItemModel(
    val fotopengguna: String,
    val namapengguna: String,
    val isichat: String,
    val hari: String
) :Parcelable
