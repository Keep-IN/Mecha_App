package com.example.mechaapp.features.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mechaapp.R
import androidx.core.widget.doOnTextChanged
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Model.RegisResponse
import com.example.mechaapp.databinding.ActivityRegisterBinding
import com.example.mechaapp.features.MainActivity

class Register : AppCompatActivity(), RegisterContract {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.tpiwAlready.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.etEmail.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validateEmail(binding.etEmail.editText?.text.toString(), )
        }

        binding.etPassword.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validatePassword(binding.etPassword.editText?.text.toString(), )
        }

        binding.etPhone.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validateTelephone(binding.etPhone.editText?.text.toString(), )
        }

        binding.etName.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
        }

        binding.etConpass.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validateRepassword(binding.etPassword.editText?.text.toString(),binding.etConpass.editText?.text.toString())
        }

        binding.btnSignup.setOnClickListener{
            presenter.emailAvailable(binding.etEmail.editText?.text.toString())
        }

        binding.btnSignup.setOnClickListener{
           presenter.regisUser(binding.etName.editText?.text.toString(),binding.etPhone.editText?.text.toString(),binding.etEmail.editText?.text.toString(),binding.etPassword.editText?.text.toString())
        }

        presenter = RegisterPresenter(this, UserAPI()).apply {
            onAtach(this@Register)
        }
    }

    private fun validateInput(){
        binding.btnSignup.isEnabled =
            binding.etEmail.editText?.text.toString().isNotBlank() &&
                    binding.etPhone.editText?.text.toString().isNotBlank() &&
                    binding.etPassword.editText?.text.toString().isNotBlank() &&
                    binding.etName.editText?.text.toString().isNotBlank() &&
                    binding.etConpass.editText?.text.toString().isNotBlank()
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onFinishedLoading() {
        TODO("Not yet implemented")
    }

    override fun onError(code: Int, message: String) {
        when(code) {
            1 ->binding.etEmail.error = message
            3 ->binding.etPassword.error = message
            4 ->binding.etPhone.error = message
            5 ->binding.etConpass.error = message
        }
    }

    override fun onSuccesRegister(user: RegisResponse?) {
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onErrorSignup(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorSuccess(code: Int, message: String) {
        when(code) {
            2 ->binding.etPassword.isErrorEnabled = false
            6 ->binding.etPhone.error = ""
            1 ->binding.etEmail.error = ""
            7 ->binding.etConpass.error = ""
        }
    }

    override fun onErrorAvailable(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}