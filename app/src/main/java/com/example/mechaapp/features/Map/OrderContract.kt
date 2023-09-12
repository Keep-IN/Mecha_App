package com.example.mechaapp.features.Map

import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.data.Model.PriceResponse

interface OrderContract {
    fun onSuccesOrder(order: OrderResponse?)
    fun onErrorOrder(msg: String)
    fun onSuccesHistory(history: OrderResponse?)
    fun onErrorhistory(msg: String)
    fun onSuccesGetOrder(order: OrderGetResponse?)
    fun onErrorgetOrder(msg: String)
    fun onSuccessPrice(price: PriceResponse?)
    fun onErrorPrice(msg: String)
}