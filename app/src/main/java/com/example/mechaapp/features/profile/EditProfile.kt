package com.example.mechaapp.features.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityEditProfileBinding
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.Login.ForgetPassword

class EditProfile : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivbackEditprofile.setOnClickListener {
                startActivity(Intent(this@EditProfile, HomeFragment::class.java))
            }
        }

    }
}