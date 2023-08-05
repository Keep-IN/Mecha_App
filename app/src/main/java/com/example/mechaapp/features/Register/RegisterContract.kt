package com.example.mechaapp.features.Register

interface RegisterContract {

    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onSuccesRegister()
    fun onErrorSignup()
    fun onErrorSuccess(code: Int, message: String)
    fun onErrorAvailable(code: Int, message: String )


}