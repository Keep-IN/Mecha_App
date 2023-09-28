package com.example.mechaapp.partner.features2.history2.detailpembayaran

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mechaapp.R
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
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataOrder.map_url))
        intent.setPackage("com.google.android.apps.maps")
        binding.cvSeeAlamat.setOnClickListener {
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(dataOrder.map_url))
                startActivity(webIntent)
            }

        }

        binding.apply {
            if (order != null) {
                tvNamauser.text = order.name
                tvAlamatpembayaran.text = order.address

                Glide
                    .with(binding.root.context)
                    .load(order.img_url)
                    .into(binding.ivGambarpembayaran)
                when(order.status){
                    "Menunggu" -> binding.ivMenunggum.setImageDrawable(ContextCompat.getDrawable(this@DetailPembayaran, R.drawable.ic_progress_indicator))
                    "Diterima" -> binding.ivDiterimam.setImageDrawable(ContextCompat.getDrawable(this@DetailPembayaran, R.drawable.ic_progress_indicator))
                    "Selesai" -> binding.ivSelesaim.setImageDrawable(ContextCompat.getDrawable(this@DetailPembayaran, R.drawable.ic_progress_indicator))
                }
            }
        }
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