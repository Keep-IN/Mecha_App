package com.example.mechaapp.features.Dashboard.Layanan.GantiBan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.databinding.ActivityLayananBanBinding
import com.example.mechaapp.databinding.BottomSheetVelgBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.Dashboard.Layanan.GantiBan.BottomSheetGantiBan

class LayananBan : AppCompatActivity() {
    private lateinit var binding: ActivityLayananBanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayananBanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            cvGantiBan.setOnClickListener {
                BottomSheetGantiBan().show(supportFragmentManager, "Ganti Ban")
            }
            cvGantiVelg.setOnClickListener {
                BottomSheetGantiVelg().show(supportFragmentManager, "Ganti Velg")
            }
            ivbackLayanan.setOnClickListener {
                startActivity(Intent(this@LayananBan, HomeFragment::class.java))
            }
        }
    }
}