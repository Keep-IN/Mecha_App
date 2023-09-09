package com.example.mechaapp.partner.features2.ambilpesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.databinding.ActivityAmbilpesanBinding
import com.example.mechaapp.data.adapter.AmbilListAdapter
import com.example.mechaapp.data.Model.DataAmbilPesanan
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.partner.features2.History2.Detail.DetailPesanan
import com.example.mechaapp.partner.home2.HomeFragment2

class AmbilPesan : AppCompatActivity(), AmbilPesanContract {
    private lateinit var binding: ActivityAmbilpesanBinding
    private lateinit var presenter: AmbilPesanPresenter
    private val adapterHistory: AmbilListAdapter by lazy { AmbilListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?){
        binding = ActivityAmbilpesanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = AmbilPesanPresenter(this, OrderAPI()).apply {
            onAttach(this@AmbilPesan)
        }
        presenter.getOrder()

        binding.impiwbackPesan.setOnClickListener {
            startActivity(Intent(this, HomeFragment2::class.java ))
        }

        val layoutManager = LinearLayoutManager(this)

        binding.rvAmbil.adapter = adapterHistory
        binding.rvAmbil.layoutManager = layoutManager

    }
    private val rvClickListener: (OrderResponse) -> Unit = {
        item ->
        startActivity(Intent(this, DetailPesanan::class.java))
    }

    override fun onSuccessGet(order: OrderGetResponse?) {
        if (order != null) {
            adapterHistory.submitList(order.order)
        }
    }

    override fun onFailedGet(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}