package com.example.mechaapp.partner.features2.ambilpesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.ActivityAmbilpesanBinding
import com.example.mechaapp.partner.data2.adapter2.AmbilListAdapter
import com.example.mechaapp.partner.data2.DataAmbilPesanan
import com.example.mechaapp.partner.home2.HomeFragment2

class AmbilPesan : AppCompatActivity() {
    private  lateinit var binding: ActivityAmbilpesanBinding
    private val adapterHistory: AmbilListAdapter by lazy { AmbilListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?){
        binding = ActivityAmbilpesanBinding.inflate(layoutInflater)

        binding.impiwbackPesan.setOnClickListener {
            startActivity(Intent(this, HomeFragment2::class.java ))
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        adapterHistory.submitList(DataAmbilPesanan.ambilpesanList)
        binding.rvAmbil.adapter = adapterHistory
        binding.rvAmbil.layoutManager = layoutManager

    }
}