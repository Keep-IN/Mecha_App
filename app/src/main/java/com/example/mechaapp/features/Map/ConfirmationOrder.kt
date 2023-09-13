package com.example.mechaapp.features.Map

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.DataAddress
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.databinding.ActivityConfirmationOrderBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.Dashboard.NavbarContainer

class ConfirmationOrder : AppCompatActivity(), OrderContract {
    private lateinit var binding: ActivityConfirmationOrderBinding
    private lateinit var presenter: OrderPresenter
    private lateinit var idService: String
    private lateinit var descService: String
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
            presenter.postOrder(binding.tvTitle.text.toString(), "Menunggu", binding.tvAlamat.text.toString(), binding.tvUrl.text.toString())
        }
        binding.ivbackConfirm.setOnClickListener {
            startActivity(Intent(this@ConfirmationOrder, MapAddress::class.java))
        }

    }

    override fun onSuccesOrder(order: OrderResponse?) {
        presenter.postHistory(order?.order?.name.toString(), order?.order?.name_service.toString(), order?.order?.status.toString(),
            order?.order?.address.toString(), order?.order?.map_url.toString(), order?.order?.id_service.toString())
        idService = order?.order?.id_service.toString()
        descService = order?.order?.name_service.toString()
    }

    override fun onErrorOrder(msg: String) {
        Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesHistory(history: OrderResponse?) {
        Toast.makeText(this, "Berhasil Order", Toast.LENGTH_SHORT).show()
        presenter.postPriceOrder(idService,descService, "50000")
        presenter.postPriceHistory(idService,descService, "50000")
        startActivity(Intent(this, NavbarContainer::class.java))
    }

    override fun onErrorhistory(msg: String) {
        Toast.makeText(this, "gagal history", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetOrder(order: OrderGetResponse?) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorgetOrder(msg: String) {
        Toast.makeText(this, "gagal get order", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessPrice(price: PriceResponse?) {
    }

    override fun onErrorPrice(msg: String) {
        runOnUiThread {
            Toast.makeText(this, "Gagal Price", Toast.LENGTH_SHORT).show()
        }
    }
}