package com.example.mechaapp.partner.features2.history2.detailpembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityDetailPembayaranBinding
import com.example.mechaapp.databinding.ActivityLandingPageBinding
import com.example.mechaapp.features.MainActivity
import com.example.mechaapp.features.Register.Register

class DetailPembayaran : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPembayaranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cvTambah.setOnClickListener {
            startActivity(Intent(this, LayananTambahan::class.java ))
        }
    }
}