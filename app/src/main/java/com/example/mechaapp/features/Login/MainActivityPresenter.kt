package com.example.mechaapp.features.Login

import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivityPresenter (
    private val view: MainActivityContract,
    private val api: UserAPI,
    uiContext: CoroutineContext = Dispatchers.Main
){
    private var isEmailValid = false
    private var isPasswordValid = false

    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAttach(view: MainActivityContract){
        this.view

    }
    fun validateEmail(email: String): Boolean {
        //validasi format email
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())

        if (isEmailValid) {
            view.onErrorEmpty(1)
        }else {
            view.onError(2, "Format email yang anda masukkan salah.")
        }
        return isEmailValid
    }
    fun validatePassword(password: String): Boolean {
        //validasi email lebih dari 7 karakter
        isPasswordValid = password.length > 7


        if (isPasswordValid) {
            view.onErrorEmpty(3)
        }else {
            view.onError(4, "Password minimal 8 huruf.")
        }
        return isPasswordValid
    }
//    fun validateCredential(){
//        if(isEmailValid && isPasswordValid){
//            view.onSuccesLogin()
//        }else {
//            view.onErrorLogin()
//        }
//    }

    fun loginUser(email: String, password: String){
        api.loginUser(email, password){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesLogin(it.data)
                    is ResponseStatus.Failed -> view.onErrorLogin()
                }
            }
        }
    }
}