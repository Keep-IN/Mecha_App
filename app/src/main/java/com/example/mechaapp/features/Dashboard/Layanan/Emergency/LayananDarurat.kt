package com.example.mechaapp.features.Dashboard.Layanan.Emergency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityLayananDaruratBinding

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
        }
    }
}