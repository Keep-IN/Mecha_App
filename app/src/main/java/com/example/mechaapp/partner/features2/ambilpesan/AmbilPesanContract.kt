package com.example.mechaapp.partner.features2.ambilpesan

import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse

interface AmbilPesanContract {
    fun onSuccessGet(order: OrderGetResponse?)
    fun onFailedGet(msg: String)
}