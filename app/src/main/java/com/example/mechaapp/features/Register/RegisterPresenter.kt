package com.example.mechaapp.features.Register

import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class RegisterPresenter(
    private val view:RegisterContract,
    private val api: UserAPI,
    uiContext: CoroutineContext = Dispatchers.Main
    ) {
    private  var isEmailValid = false
    private var isPasswordValid = false
    private var isTelephoneValid = false
    private var isValidateRepassword =  false
    private var isEmailAvailable = false

    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAtach(view: RegisterContract) {
        this.view
    }
        fun validateEmail(email: String): Boolean {
            val isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
            if (!isEmailValid) {
                view?.onError(1, "Format email tidak sesuai")
            } else {
                view?.onErrorSuccess(1,"")
            }
            return isEmailValid
        }

        fun validatePassword (password: String): Boolean {
            val isPasswordValid = password.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
            if (isPasswordValid) {
                view.onErrorSuccess(2,"")
            } else {
                view.onError(3,"Password minimal 8 huruf dan kata sandi harus terdiri dari angka dan huruf")
            }
            return isPasswordValid
        }

        fun validateTelephone (phone: String): Boolean {
            this.isTelephoneValid = phone.length in 9..13
            if (isTelephoneValid) {
                view.onErrorSuccess(6,"")
            } else {
                view.onError(4,"Jumlah nomor telpon terdiri dari 10-12")
            }
            return isTelephoneValid
        }

        fun validateRepassword (password: String, repassword: String) : Boolean {
            isValidateRepassword = repassword.equals(password)
            if (isValidateRepassword) {
                view.onErrorSuccess(7, "")
            } else {
                view.onError(5,"Password tidak sama")

            }
            return isValidateRepassword
        }

        fun emailAvailable (email: String) : Boolean {
            isEmailAvailable = email == "ghazygg@gmail.com"
            if (email == "ghazygg@gmail.com") {
                view.onErrorAvailable(8,"Email Sudah Dipakai")
            }
            return isEmailAvailable
        }
    fun regisUser(nama: String, email: String, notelp: String, password: String){
        api.regisUser(nama,email,notelp,password) {
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesRegister(it.data)
                    is ResponseStatus.Failed -> view.onErrorSignup(it.message)
                }
            }
        }
    }
}