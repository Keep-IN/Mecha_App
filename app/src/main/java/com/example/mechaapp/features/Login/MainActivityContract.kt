package com.example.mechaapp.features.Login

interface MainActivityContract {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onSuccesUsername()
    fun onSuccesPassword()
    fun onSuccesLogin()
    fun onErrorLogin()
    fun onErrorEmpty(code: Int)
    fun onErrorFalse(code: Int, message: String)
}