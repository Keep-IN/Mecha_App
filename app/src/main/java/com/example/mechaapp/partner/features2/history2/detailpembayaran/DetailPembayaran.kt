package com.example.mechaapp.partner.features2.history2.detailpembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Model.DataPelanggan
import com.example.mechaapp.data.Model.DataPrice
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.data.Model.UserResponse
import com.example.mechaapp.data.adapter.DetailMontirAdapter
import com.example.mechaapp.databinding.ActivityDetailPembayaranBinding

class DetailPembayaran : AppCompatActivity(),BayarContract {
    private lateinit var binding: ActivityDetailPembayaranBinding
    private lateinit var dataOrder: OrderModel
    private lateinit var presenter: BayarPresenter
    private val adapterDetailmontir: DetailMontirAdapter by lazy { DetailMontirAdapter () }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPembayaranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val order = intent.getParcelableExtra<OrderModel>("order")
        if (order != null) {
            dataOrder = order
            if(order.status == "Selesai"){
                binding.cvKirimResi.visibility = View.GONE
            }else{
                binding.cvKirimResi.visibility = View.VISIBLE
            }
        }
        presenter = BayarPresenter(this, OrderAPI(), UserAPI()).apply {
            onAttach(this@DetailPembayaran)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvDetailpembayaran.adapter = adapterDetailmontir
        binding.rvDetailpembayaran.layoutManager = layoutManager

        binding.cvKirimResi.setOnClickListener {
            startActivity(Intent(this, KonfirmasiPembayaran::class.java ))
        }

        binding.cvTambah.setOnClickListener {
            startActivity(Intent(this, LayananTambahan::class.java ).apply {
                putExtra("order", order)
            })
        }
        binding.cvKirimResi.setOnClickListener {
            DataPrice.priceList.forEach {
                presenter.postPriceByName("${DataPelanggan.id}/${dataOrder.id_service}", it.description_service, it.price)
            }
            presenter.updateStatus("Diterima", dataOrder.id_service)
        }
        presenter.getPriceById(dataOrder.id.toString(),dataOrder.id_service)
    }

    override fun onStart() {
        super.onStart()
        presenter.getPriceById(dataOrder.id.toString(),dataOrder.id_service)
    }

    override fun onSuccesPrice(price: PriceResponse?) {
        Log.d("Succes", "Price Berhasil")
    }

    override fun onErrorPrice(msg: String) {
        Toast.makeText(this, "Gagal Post Price", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {
        Log.d("Succes", "Get Price Berhasil")
        val sumPrice: MutableList<Int> = mutableListOf()
        if (price != null) {
            runOnUiThread {
                adapterDetailmontir.submitList(price.price)
                price.price.forEach {
                    sumPrice.add(it.price.toInt())
                }
                binding.tvSumPriceMontir.text = "Rp ${sumPrice.sum().formatDecimalSeparator()}"
            }
            DataPrice.priceList = price.price.toMutableList()
        }
        presenter.getAllUser()
    }

    override fun onErrorGetPrice(msg: String) {
        Toast.makeText(this, "Gagal Get Price", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesuser(user: UserResponse?) {
        runOnUiThread {
            user?.user?.forEach {
                if (it.name.contains(dataOrder.name)){
                    DataPelanggan.id = it.id
                    Log.d("User ID", DataPelanggan.id.toString())
                    Log.d("Succes", "Get User Berhasil")
                }
            }
        }
    }

    override fun onError(msg: String) {
        Log.d("Error", "API Hit Not found 404")
    }

    override fun onSuccesUpdate(status: StatusResponse?) {
        startActivity(Intent(this, KonfirmasiPembayaran::class.java).apply {
            putExtra("order", dataOrder)
        })
    }

    fun Int.formatDecimalSeparator(): String {
        return toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    }
}