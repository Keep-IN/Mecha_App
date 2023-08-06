package com.example.mechaapp.features.Login

class MainActivityPresenter (
    private val view: MainActivityContract
        ) {
    private var isEmailValid = false
    private var isPasswordValid = false
    private var isEmailCorrect = false
    private var isPasswordCorrect = false

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
    fun validateCredential(email: String, password: String){
        //dummy email + password
        isEmailCorrect= email == "Mecha@gmail.com"
        isPasswordCorrect = password == "12345678Mecha"

        when(isEmailCorrect){
            //fungsi cek email
            true -> view.onErrorEmpty(7)
            false -> view.onErrorFalse(5, "Email anda tidak terdaftar!")
        }

        when(isPasswordCorrect){
            //fungsi cek password
            true -> view.onErrorEmpty(8)
            false -> view.onErrorFalse(6, "Password salah!")
        }

        //testing email+password bener atau salah
        if(isEmailCorrect && isPasswordCorrect) {
            view.onSuccesLogin()
        }
    }
}