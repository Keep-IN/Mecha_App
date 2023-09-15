package com.example.mechaapp.partner.features2.ambilpesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.databinding.ActivityAmbilpesanBinding
import com.example.mechaapp.data.adapter.AmbilListAdapter
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.partner.features2.history2.Detail.DetailPesanan

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
           onBackPressedDispatcher.onBackPressed()
        }

        val layoutManager = LinearLayoutManager(this)

        binding.rvAmbil.adapter = adapterHistory
        binding.rvAmbil.layoutManager = layoutManager
        adapterHistory.setOnclickItem(rvClickListener)

    }
    private val rvClickListener: ((OrderModel) -> Unit) = {
        item ->
        startActivity(Intent(this, DetailPesanan::class.java).apply {
            putExtra("order", item)
        })
    }

    override fun onSuccessGet(order: OrderGetResponse?) {
        if (order != null) {
            Log.d("MyApp", "Posting price for: ${order.order}")
            adapterHistory.submitList(order.order)
        }
    }

    override fun onFailedGet(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}