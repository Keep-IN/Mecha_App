package com.example.mechaapp.partner.features2.History2.detailpembayaran

import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.UserResponse

interface BayarContract {
    fun onSuccesPrice(price: PriceResponse?)
    fun onErrorPrice(msg: String)
    fun onSuccesGetPrice(price: PriceGetResponse?)
    fun onErrorGetPrice(msg: String)
    fun onSuccesuser(user: UserResponse?)
    fun onError(msg: String)
}