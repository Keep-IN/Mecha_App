package com.example.mechaapp.features.Login

import com.example.mechaapp.data.Model.LoginResponse

interface MainActivityContract {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onSuccesUsername()
    fun onSuccesPassword()
    fun onSuccesLogin(user: LoginResponse?)
    fun onErrorLogin()
    fun onErrorEmpty(code: Int)
    fun onErrorFalse(code: Int, message: String)
}