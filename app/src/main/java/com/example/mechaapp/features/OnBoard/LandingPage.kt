package com.example.mechaapp.features.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.databinding.ActivityLandingPageBinding
import com.example.mechaapp.features.MainActivity
import com.example.mechaapp.features.Register.Register
import com.example.mechaapp.features.chat.IsiChat
import com.example.mechaapp.partner.features2.history2.detailpembayaran.DetailPembayaran

class LandingPage : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btntoLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java ))
        }

        binding.btnToRegis.setOnClickListener {
            startActivity(Intent(this, Register::class.java ))
        }
    }
}