package com.example.mechaapp.features.Map

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.mechaapp.features.profile.AlertDialogKeluarAkun

class ConfirmationOrder : AppCompatActivity(), OrderContract {
    private lateinit var binding: ActivityConfirmationOrderBinding
    private lateinit var presenter: OrderPresenter
    private lateinit var idService: String
    private lateinit var descService: String
    private var layanan: String = ""
    val url = Intent(android.content.Intent.ACTION_VIEW)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfirmationOrderBinding.inflate(layoutInflater)
        url.data = Uri.parse(DataAddress.mapUrl)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        layanan = intent.getStringExtra("layanan").toString()
        presenter = OrderPresenter(this, OrderAPI()).apply {
            onAttach(this@ConfirmationOrder)
        }

        binding.apply{
            tvAlamat.text = DataAddress.address
            tvUrl.text = DataAddress.mapUrl
            tvUrl.setOnClickListener {
                startActivity(url)
            }
            btnPesan.setOnClickListener {
                AlertConfirmationOrder().show(supportFragmentManager,"test")
            }
        }
        binding.btnPesan.setOnClickListener {
            presenter.postOrder(layanan, "Menunggu", binding.tvAlamat.text.toString(), binding.tvUrl.text.toString())
        }
        binding.ivbackConfirm.setOnClickListener {
            startActivity(Intent(this@ConfirmationOrder, MapAddress::class.java))
        }
        runOnUiThread {
            var harga = 0
            when(layanan){
                "Ganti Ban" -> harga = 15000
                "Ganti Velg" -> harga = 15000
                "Ganti Oli" -> harga = 30000
                "Kendaraan Mogok" -> harga = 30000
                "Tambal Ban" ->harga = 15000
                "Injeksi Motor" -> harga = 110000
                "Karburator Motor" ->harga = 110000
            }
            binding.tvHarga.text = "Rp ${harga.formatDecimalSeparator()}"
            binding.tvBiaya.text = "Rp ${(harga*20/100).formatDecimalSeparator()}"
            binding.tvSumLayanan.text = "Rp ${(harga+(harga*20/100)).formatDecimalSeparator()}"
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
        var price = "0"
        when(layanan){
            "Ganti Ban" -> price = "15000"
            "Ganti Velg" -> price = "15000"
            "Ganti Oli" -> price = "30000"
            "Kendaraan Mogok" -> price = "30000"
            "Tambal Ban" -> price = "15000"
            "Injeksi Motor" -> price = "111000"
            "Karburator Motor" -> price = "110000"
        }
        val profit = (price.toInt()*20)/100
        //Post Price to Order
        presenter.postPriceOrder(idService, "Biaya Pemakaian Aplikasi", profit.toString())
        presenter.postPriceOrder(idService,descService, price)
        //Post Price to History
        presenter.postPriceHistory(idService, "Biaya Pemakaian Aplikasi", profit.toString())
        presenter.postPriceHistory(idService,descService, price)
        AlertConfirmationOrder().show(supportFragmentManager, "Sukses")
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, NavbarContainer::class.java))
            finishAffinity()
        }, 3000)
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
    fun Int.formatDecimalSeparator(): String {
        return toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    }
}