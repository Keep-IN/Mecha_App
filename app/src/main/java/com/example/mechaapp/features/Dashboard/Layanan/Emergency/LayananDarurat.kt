package com.example.mechaapp.features.Dashboard.Layanan.Emergency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityLayananDaruratBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.OnBoard.LandingPage

class LayananDarurat : AppCompatActivity() {
    private lateinit var binding: ActivityLayananDaruratBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayananDaruratBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            cvMogok.setOnClickListener {
                BottomSheetMogok().show(supportFragmentManager, "Mogok di jalan")
            }
            cvTambal.setOnClickListener {
                BottomSheetTambal().show(supportFragmentManager, "Tambal Ban")
            }
            ivbackLayanan.setOnClickListener {
                startActivity(Intent(this@LayananDarurat, HomeFragment::class.java))
            }
        }
    }
}