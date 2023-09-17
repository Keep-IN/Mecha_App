package com.example.mechaapp.features.DetailPesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.adapter.DetailMontirAdapter
import com.example.mechaapp.databinding.ActivityDetailPesananBinding
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.History.HistoryFragment
import com.example.mechaapp.features.Login.ForgetPassword

class DetailPesanan : AppCompatActivity(), DetailPesananContract {
    private lateinit var binding: ActivityDetailPesananBinding
    private lateinit var dataOrder: OrderModel
    private lateinit var presenter: DetailPesananPresenter
    private val adapterDetailuser: DetailMontirAdapter by lazy { DetailMontirAdapter () }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val order = intent.getParcelableExtra<OrderModel>("order")
        if (order != null) {
            dataOrder = order
        }
        presenter = DetailPesananPresenter(this, OrderAPI()).apply {
            onAttach(this@DetailPesanan)
        }
        presenter.getPriceById(dataOrder.id.toString(), dataOrder.id_service)

        val layoutManager = LinearLayoutManager(this)
        binding.rvDetailuser.adapter = adapterDetailuser
        binding.rvDetailuser.layoutManager = layoutManager
        binding.apply {
            tvNamaUser.text = dataOrder.name
            tvAlamatUser.text = dataOrder.address
        }

        binding.cvMetode.setOnClickListener {
            startActivity(Intent(this, MetodePembayaran::class.java ))
        }
        binding.ivBackdetail.setOnClickListener {
            startActivity(Intent(this, HistoryFragment::class.java ))
        }
        binding.cvPayService.setOnClickListener {
            startActivity(Intent(this, MenungguKonfirmasiPembayaran::class.java).apply {
                putExtra("order", order)
            })
        }

        binding.apply {
            if (order != null) {
                tvNamaUser.text = order.name
                tvAlamatUser.text = order.address

                Glide
                    .with(binding.root.context)
                    .load(order.img_url)
                    .into(binding.ivGambardetail)
                when(order.status){
                    "Menunggu" -> binding.ivMenunggu.setImageDrawable(ContextCompat.getDrawable(this@DetailPesanan, R.drawable.ic_progress_indicator))
                    "Diterima" -> binding.ivDiterima.setImageDrawable(ContextCompat.getDrawable(this@DetailPesanan, R.drawable.ic_progress_indicator))
                    "Selesai" -> binding.ivSelesai.setImageDrawable(ContextCompat.getDrawable(this@DetailPesanan, R.drawable.ic_progress_indicator))
                }
            }
        }
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {
        Log.d("Succes", "Get Price Berhasil")
        val sumPrice: MutableList<Int> = mutableListOf()
        if (price != null) {
            runOnUiThread {
                adapterDetailuser.submitList(price.price.distinct())
                price.price.forEach {
                    sumPrice.add(it.price.toInt())
                }
                binding.tvSumPrice.text = "Rp ${sumPrice.sum().formatDecimalSeparator()}"
            }
        }
    }

    override fun onErrorGetPrice(msg: String) {
        Log.d("Error", "Error Get Price")
    }

    override fun onSuccesGetStatus(history: HistoryGetResponse?) {
    }

    override fun onErrorGetStatus(msg: String) {
    }

    fun Int.formatDecimalSeparator(): String {
        return toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    }
}