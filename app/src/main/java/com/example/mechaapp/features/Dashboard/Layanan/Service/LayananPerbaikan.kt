package com.example.mechaapp.features.Dashboard.Layanan.Service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityLayananBanBinding
import com.example.mechaapp.databinding.ActivityLayananPerbaikanBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.Dashboard.Layanan.GantiBan.BottomSheetGantiBan
import com.example.mechaapp.features.Dashboard.Layanan.GantiBan.BottomSheetGantiVelg

class LayananPerbaikan : AppCompatActivity() {
    private lateinit var binding: ActivityLayananPerbaikanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayananPerbaikanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

            ivbackLayanan.setOnClickListener {
                startActivity(Intent(this@LayananPerbaikan, HomeFragment::class.java))
            }
        }
    }
}