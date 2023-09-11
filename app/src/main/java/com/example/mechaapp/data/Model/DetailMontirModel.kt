package com.example.mechaapp.data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMontirModel (
    val juduldetail: String,
    val judulharga: String
): Parcelable