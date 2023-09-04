package com.example.mechaapp.features.DetailPesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.R
import com.example.mechaapp.data.adapter.SpotlightListAdapter
import com.example.mechaapp.databinding.ActivityMetodePembayaranBinding
import com.example.mechaapp.databinding.FragmentHistoryBinding
import com.example.mechaapp.features.Data.Adapter.MetodePembayaranAdapter
import com.example.mechaapp.features.Data.Model.DataPembayaran

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
    }
}