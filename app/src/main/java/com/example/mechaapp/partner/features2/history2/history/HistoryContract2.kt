package com.example.mechaapp.partner.features2.history2.history

import com.example.mechaapp.data.Model.HistoryGetResponse

interface HistoryContract2 {
    fun onSucces(history: HistoryGetResponse?)
    fun onFailed(msg: String)

}