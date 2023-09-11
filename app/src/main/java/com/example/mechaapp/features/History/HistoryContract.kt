package com.example.mechaapp.features.History

import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse

interface HistoryContract {
    fun onSucces(history: OrderGetResponse?)
    fun onFailed(msg: String)
}