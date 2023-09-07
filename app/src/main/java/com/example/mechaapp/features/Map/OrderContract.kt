package com.example.mechaapp.features.Map

import com.example.mechaapp.data.Model.OrderResponse

interface OrderContract {
    fun onSuccesOrder(order: OrderResponse?)
    fun onErrorOrder(msg: String)
}