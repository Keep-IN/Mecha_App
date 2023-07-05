package com.example.mechaapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.mechaapp.ForgetPassword
import com.example.mechaapp.features.Register.Register
import com.example.mechaapp.databinding.ActivityMainBinding

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

        presenter = MainActivityPresenter(this).apply{
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
            // cek eror button masuk
            presenter.validateCredential(binding.tilemailLogin.editText?.text.toString(),
                binding.tilpwLogin.editText?.text.toString())
        }
        binding.btntoDaftar.setOnClickListener {
            startActivity(Intent(this, Register::class.java ))
        }

    }

    private fun validateInput(){
        binding.btnToLogin.isEnabled = binding.tilemailLogin.editText?.text.toString().isNotBlank() &&
                binding.tilpwLogin.editText?.text.toString().isNotBlank()
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onFinishedLoading() {
        TODO("Not yet implemented")
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

    override fun onSuccesLogin() {
        //fungsi pindah login ke dashboard + allert
        //startActivity(Intent(this, NavBarContainer::class.java))
       // Toast.makeText(this, "login berhasil", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorLogin(code: Int, message: String) {
        TODO("Not yet implemented")
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