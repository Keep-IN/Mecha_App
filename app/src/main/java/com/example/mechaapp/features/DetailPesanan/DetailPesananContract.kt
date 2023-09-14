package com.example.mechaapp.features.DetailPesanan

import com.example.mechaapp.data.Model.PriceGetResponse

interface DetailPesananContract {
    fun onSuccesGetPrice(price: PriceGetResponse?)
    fun onErrorGetPrice(msg: String)
}