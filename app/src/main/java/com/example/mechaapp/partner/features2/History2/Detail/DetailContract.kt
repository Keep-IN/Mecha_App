package com.example.mechaapp.partner.features2.History2.Detail

import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.StatusResponse

interface DetailContract {
    fun onSuccesHistory(order: OrderResponse?)
    fun onErrorHistory(msg: String)
    fun onSucceUpdate(data: StatusResponse?)
    fun onSuccessDelete(order: OrderResponse?)
    fun onErrorUpdate(msg: String)
    fun onErrorDelete(msg: String)
}