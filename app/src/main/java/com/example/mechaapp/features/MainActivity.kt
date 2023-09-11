package com.example.mechaapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.DataUser
import com.example.mechaapp.data.Model.LoginResponse
import com.example.mechaapp.features.Login.ForgetPassword
import com.example.mechaapp.features.Register.Register
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.Dashboard.NavbarContainer
import com.example.mechaapp.features.Login.AlertDialog.AlertDialogLoginGagal
import com.example.mechaapp.features.Login.AlertDialog.AlertDialogLoginSucces
import com.example.mechaapp.features.Login.MainActivityContract
import com.example.mechaapp.features.Login.MainActivityPresenter
import com.example.mechaapp.partner.home2.NavbarContainer2

class MainActivity : AppCompatActivity(), MainActivityContract {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvForget.setOnClickListener {
            startActivity(Intent(this, ForgetPassword::class.java ))
        }

        //binding.ivBackLogin.setOnClickListener {
           // onBackPressedDispatcher.onBackPressed()
      // }

        binding.btnToLogin.isEnabled = false

        presenter = MainActivityPresenter(this, UserAPI()).apply{
            onAttach(this@MainActivity)
        }

        binding.tilemailLogin.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validateEmail(binding.tilemailLogin.editText?.text.toString())
        }

        binding.tilpwLogin.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validatePassword((binding.tilpwLogin.editText?.text.toString()))
        }

        binding.btnToLogin.setOnClickListener{
                binding.tilpwLogin.editText?.text.toString()
        }
        binding.btntoDaftar.setOnClickListener {
            startActivity(Intent(this, Register::class.java ))
        }
        binding.btnToLogin.setOnClickListener {
            onLoading()
            presenter.loginUser(binding.tilemailLogin.editText?.text.toString(),
                binding.tilpwLogin.editText?.text.toString())
        }
    }


    private fun validateInput(){
        binding.btnToLogin.isEnabled = binding.tilemailLogin.editText?.text.toString().isNotBlank() &&
                binding.tilpwLogin.editText?.text.toString().isNotBlank()
    }

    override fun onLoading() {
        binding.bgLoading.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun onFinishedLoading() {
        binding.bgLoading.visibility = View.GONE
        binding.pbLoading.visibility = View.GONE
    }

    override fun onError(code: Int, message: String) {
        when(code){
            // nampilin error message
            2 -> binding.tilemailLogin.error = message
            4 -> binding.tilpwLogin.error = message
        }
    }

    override fun onSuccesUsername() {
        TODO("Not yet implemented")
    }

    override fun onSuccesPassword() {
        TODO("Not yet implemented")
    }

    override fun onSuccesLogin(user: LoginResponse?) {
        DataToken.token = user?.token.toString()
        DataUser.nama = user?.nama.toString()
        AlertDialogLoginSucces().show(supportFragmentManager,"test")
        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null) {
                when(user.role){
                    1 -> startActivity(Intent(this, NavbarContainer::class.java))
                    2 -> startActivity(Intent(this, NavbarContainer2::class.java))
                }
            }
            finish()
        }, 2000)
    }

    override fun onErrorLogin() {
        AlertDialogLoginGagal().show(supportFragmentManager,"test")
    }

    override fun onErrorEmpty(code: Int) {
        when(code){
            //ngilangin error message
            1 -> binding.tilemailLogin.error=""
            3 -> binding.tilpwLogin.error=""
        }
    }

    override fun onErrorFalse(code: Int, message: String) {
        when(code){
            5 -> binding.tilemailLogin.error=message
            6 -> binding.tilpwLogin.error=message
        }
    }
}