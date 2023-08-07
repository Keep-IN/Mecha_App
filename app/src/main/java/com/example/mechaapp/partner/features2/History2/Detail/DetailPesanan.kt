package com.example.mechaapp.partner.features2.History2.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityDetailPesananBinding

class DetailPesanan : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPesananBinding
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}