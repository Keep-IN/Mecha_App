package com.example.mechaapp.features.Map

import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse

interface OrderContract {
    fun onSuccesOrder(order: OrderResponse?)
    fun onErrorOrder(msg: String)
    fun onSuccesHistory(history: OrderResponse?)
    fun onErrorhistory(msg: String)
    fun onSuccesGetOrder(order: OrderGetResponse?)
}