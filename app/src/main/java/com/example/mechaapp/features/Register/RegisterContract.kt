package com.example.mechaapp.features.Register

import com.example.mechaapp.data.Model.RegisResponse

interface RegisterContract {

    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onSuccesRegister(user: RegisResponse?)
    fun onErrorSignup()
    fun onErrorSuccess(code: Int, message: String)
    fun onErrorAvailable(code: Int, message: String )


}