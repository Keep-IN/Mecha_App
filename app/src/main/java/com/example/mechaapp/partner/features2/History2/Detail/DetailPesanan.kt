package com.example.mechaapp.partner.features2.History2.Detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.databinding.ActivityDetailPesananBinding
import com.example.mechaapp.databinding.ActivityDetailPesananMontirBinding
import com.example.mechaapp.partner.home2.NavbarContainer2

class DetailPesanan : AppCompatActivity(), DetailContract {
    private lateinit var binding: ActivityDetailPesananMontirBinding
    private lateinit var presenter: DetailPresenter
    private lateinit var dataOrder: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananMontirBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val order = intent.getParcelableExtra<OrderModel>("order")
        if (order != null) {
            dataOrder = order
        }

        presenter = DetailPresenter(this, OrderAPI()).apply {
            onAttach(this@DetailPesanan)
        }
        binding.btnAccept.setOnClickListener {
            if (order != null) {
                presenter.deleteOrder(order.id.toString())
            }
        }
    }

    override fun onSuccesHistory(order: OrderResponse?) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, NavbarContainer2::class.java))
        finishAffinity()
    }

    override fun onErrorHistory(msg: String) {
        Toast.makeText(this, "Gagal history", Toast.LENGTH_SHORT).show()
    }

    override fun onSucceUpdate(data: StatusResponse?) {
        Toast.makeText(this, "Berhasil Update", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDelete(order: OrderResponse?) {
        presenter.postHistory(dataOrder.name_service, "Diterima",
            dataOrder.address, dataOrder.map_url, dataOrder.id_service)
        presenter.updateStatus(dataOrder.id_service, "Diterima" )
    }

    override fun onErrorUpdate(msg: String) {
        Toast.makeText(this, "gagal update", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorDelete(msg: String) {
        Toast.makeText(this, "gagal delete", Toast.LENGTH_SHORT).show()
    }
}