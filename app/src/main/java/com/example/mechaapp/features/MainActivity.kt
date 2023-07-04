package com.example.mechaapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.features.Register.Register
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.Dashboard.NavbarContainer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.keDaftar.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        binding.btnToLogin.setOnClickListener {
            startActivity(Intent(this, NavbarContainer::class.java))
        }
    }
}