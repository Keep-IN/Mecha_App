package com.example.mechaapp.partner.features2.history2.detailpembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.data.Model.UserResponse
import com.example.mechaapp.databinding.ActivityKonfirmasiPembayaranBinding
import com.example.mechaapp.partner.home2.NavbarContainer2

class KonfirmasiPembayaran : AppCompatActivity(), BayarContract {
    private lateinit var binding: ActivityKonfirmasiPembayaranBinding
    private lateinit var presenter: BayarPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKonfirmasiPembayaranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        presenter = BayarPresenter(this, OrderAPI(), UserAPI()).apply {
            onAttach(this@KonfirmasiPembayaran)
        }
        val order = intent.getParcelableExtra<OrderModel>("order")
        binding.cvKonfirmasiBayar.setOnClickListener {
            if (order != null) {
                presenter.updateStatus("Selesai", order.id_service)
            }
        }
    }

    override fun onSuccesPrice(price: PriceResponse?) {
    }

    override fun onErrorPrice(msg: String) {
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {
    }

    override fun onErrorGetPrice(msg: String) {
    }

    override fun onSuccesuser(user: UserResponse?) {
    }

    override fun onError(msg: String) {
        Log.d("Error", "Gagal update status")
    }

    override fun onSuccesUpdate(status: StatusResponse?) {
        AlertDialogKeluarAkun()
        startActivity(Intent(this, NavbarContainer2::class.java))
        finishAffinity()
    }
}