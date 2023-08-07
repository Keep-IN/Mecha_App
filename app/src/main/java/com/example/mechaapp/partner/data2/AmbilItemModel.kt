package com.example.mechaapp.partner.data2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AmbilItemModel (
    val nama: String,
    val merk: String,
    val jenis: String,
    val harga: String
        ): Parcelable
