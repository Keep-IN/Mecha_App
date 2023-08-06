package com.example.mechaapp.features.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.mechaapp.databinding.ActivityOpenApp2Binding

class OpenApp2 : AppCompatActivity() {
    private lateinit var binding: ActivityOpenApp2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOpenApp2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LandingPage::class.java))
            finish()
        }, 3000)
    }
}