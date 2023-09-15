package com.example.mechaapp.features.DetailPesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.databinding.ActivityMenungguKonfirmasiPembayaranBinding
import com.example.mechaapp.features.Dashboard.NavbarContainer
import com.example.mechaapp.features.History.HistoryPresenter
import com.example.mechaapp.partner.features2.history2.detailpembayaran.AlertBayarSukses
import com.example.mechaapp.partner.home2.NavbarContainer2

class MenungguKonfirmasiPembayaran : AppCompatActivity(), DetailPesananContract {
    private lateinit var binding: ActivityMenungguKonfirmasiPembayaranBinding
    private lateinit var dataOrder: OrderModel
    private lateinit var presenter: DetailPesananPresenter
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenungguKonfirmasiPembayaranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = DetailPesananPresenter(this, OrderAPI()).apply {
            onAttach(this@MenungguKonfirmasiPembayaran)
        }
        val order = intent.getParcelableExtra<OrderModel>("order")
        if (order != null) {
            dataOrder = order
            runOnUiThread {
                val runnable = object : Runnable {
                    override fun run() {
                        presenter.getHistory(DataToken.idUser)
                        handler.postDelayed(this, 3000)
                    }
                }
                handler.post(runnable)
            }
        }
    }
    fun stopTimedLoop() {
        handler.removeCallbacksAndMessages(null)
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetPrice(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccesGetStatus(history: HistoryGetResponse?) {

        history?.history?.forEach {
            if (it.id_service == dataOrder.id_service && it.status == "Selesai"){
                stopTimedLoop()
                AlertBayarSukses().show(supportFragmentManager, "Sukses")
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, NavbarContainer::class.java))
                    finishAffinity()
                }, 2000)
            }
        }
    }

    override fun onErrorGetStatus(msg: String) {
    }

    override fun onBackPressed() {
        Log.d("Back Pressed", "Do Nothing")
    }
}