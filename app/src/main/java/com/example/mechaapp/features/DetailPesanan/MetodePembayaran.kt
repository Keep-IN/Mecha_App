package com.example.mechaapp.features.DetailPesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.ActivityMetodePembayaranBinding
import com.example.mechaapp.data.adapter.MetodePembayaranAdapter
import com.example.mechaapp.data.Model.DataPembayaran
import com.example.mechaapp.features.History.HistoryFragment

class MetodePembayaran : AppCompatActivity() {
    private lateinit var binding: ActivityMetodePembayaranBinding
    private val adapterMetode: MetodePembayaranAdapter by lazy { MetodePembayaranAdapter () }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetodePembayaranBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this)
        adapterMetode.submitList(DataPembayaran.metodeList)
        binding.rvMetode.adapter = adapterMetode
        binding.rvMetode.layoutManager = layoutManager

        binding.ivMetode.setOnClickListener {
            startActivity(Intent(this, DetailPesanan::class.java ))
        }
    }
}