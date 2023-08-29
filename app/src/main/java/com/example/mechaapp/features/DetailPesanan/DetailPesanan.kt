package com.example.mechaapp.features.DetailPesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityDetailPesananBinding
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.Login.ForgetPassword

class DetailPesanan : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.cvMetode.setOnClickListener {
            startActivity(Intent(this, MetodePembayaran::class.java ))
        }
    }
}