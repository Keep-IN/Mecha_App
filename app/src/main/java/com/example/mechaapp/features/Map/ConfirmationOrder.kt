package com.example.mechaapp.features.Map

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.DataAddress
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.databinding.ActivityConfirmationOrderBinding
import com.example.mechaapp.features.Dashboard.HomeFragment

class ConfirmationOrder : AppCompatActivity(), OrderContract {
    private lateinit var binding: ActivityConfirmationOrderBinding
    private lateinit var presenter: OrderPresenter
    val url = Intent(android.content.Intent.ACTION_VIEW)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfirmationOrderBinding.inflate(layoutInflater)
        url.data = Uri.parse(DataAddress.mapUrl)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = OrderPresenter(this, OrderAPI()).apply {
            onAttach(this@ConfirmationOrder)
        }

        binding.apply{
            tvAlamat.text = DataAddress.address
            tvUrl.text = DataAddress.mapUrl
            tvUrl.setOnClickListener {
                startActivity(url)
            }
        }
        binding.btnPesan.setOnClickListener {
            presenter.postOrder(binding.tvTitle.text.toString(), binding.tvSubtitle.text.toString(), binding.tvAlamat.text.toString(), binding.tvUrl.text.toString())
        }
        binding.ivbackConfirm.setOnClickListener {
            startActivity(Intent(this@ConfirmationOrder, MapAddress::class.java))
        }

    }

    override fun onSuccesOrder(order: OrderResponse?) {
        Toast.makeText(this, "Order Berhasil", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorOrder(msg: String) {
        Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesHistory(history: OrderResponse?) {
        TODO("Not yet implemented")
    }

    override fun onErrorhistory(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccesGetOrder(order: OrderGetResponse?) {
        TODO("Not yet implemented")
    }
}