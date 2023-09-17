package com.example.mechaapp.partner.home2

import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.TagihanResponse

interface HomeContract2 {
    fun onSucces(history: HistoryGetResponse?)
    fun onFailed(msg: String)
    fun onSuccesUpdate(tagihan: TagihanResponse?)
}