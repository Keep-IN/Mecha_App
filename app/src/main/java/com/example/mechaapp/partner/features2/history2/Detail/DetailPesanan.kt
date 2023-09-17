package com.example.mechaapp.partner.features2.history2.Detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.DataUser
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.databinding.ActivityDetailPesananMontirBinding
import com.example.mechaapp.features.Map.AlertConfirmationOrder
import com.example.mechaapp.partner.features2.History2.Detail.AlertAmbilPesan
import com.example.mechaapp.partner.home2.NavbarContainer2
import java.math.BigDecimal
import java.math.RoundingMode


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
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataOrder.map_url))
        binding.apply {
            if (order != null) {
                tvidPmontir.text = order.id_service
                tvnamaPmontir.text = order.name
                tvAlamatuserm.text = order.address
                tvLayananm.text = order.name_service

                Glide
                    .with(binding.root.context)
                    .load(order.img_url)
                    .into(binding.ivpMontir)
                cvLokasiuser.setOnClickListener {
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }

                }
            }

            btnAccept.setOnClickListener {
                AlertAmbilPesan().show(supportFragmentManager,"test")
            }
        }
    }

    override fun onSuccesHistory(order: OrderResponse?) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
        dataOrder.prices.forEach {
            if (order != null) {
                presenter.postPriceById("by/id/${order.order?.id.toString()}", it.description_service, it.price)
            }
        }
        startActivity(Intent(this, NavbarContainer2::class.java))
        finishAffinity()
//        DataPrice.priceList.forEach {
//            runOnUiThread {
//                if (order != null) {
//                    presenter.postPriceById(order.order?.id_service ?: "", it.description_service, it.price)
//                }
//            }
//        }
    }

    override fun onErrorHistory(msg: String) {
        Toast.makeText(this, "Gagal history", Toast.LENGTH_SHORT).show()
    }

    override fun onSucceUpdate(data: StatusResponse?) {
        Toast.makeText(this, "Berhasil Update", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDelete(order: OrderResponse?) {
        runOnUiThread {
            Log.d("Isi Order", "${dataOrder.prices}")
            presenter.updateStatus(dataOrder.id_service, "Diterima")
            presenter.updateName(DataUser.nama, dataOrder.user_id.toString(), dataOrder.id_service)
//            presenter.getPriceById(dataOrder.id_service)
            presenter.postHistory(dataOrder.name, dataOrder.name_service, "Diterima",
                dataOrder.address, dataOrder.map_url, dataOrder.id_service)
            }
    }

    override fun onErrorUpdate(msg: String) {
        Toast.makeText(this, "gagal update", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorDelete(msg: String) {
        Toast.makeText(this, "gagal delete", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesPrice(price: PriceResponse?) {
        Toast.makeText(this, "Sukses Price", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorPrice(msg: String) {
        Toast.makeText(this, "Gagal Price", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {
//        presenter.updateName(DataUser.nama, dataOrder.user_id.toString(), dataOrder.id_service)
//        if (price != null) {
//            DataPrice.priceList = price.price.toMutableList()
//        }
    }
}
