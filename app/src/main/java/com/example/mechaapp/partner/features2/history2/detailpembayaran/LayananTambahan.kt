package com.example.mechaapp.partner.features2.history2.detailpembayaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Model.DataPrice
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.DataUser
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.data.Model.UserResponse
import com.example.mechaapp.data.adapter.LayananTambahAdapter
import com.example.mechaapp.databinding.ActivityLayananTambahanBinding


class LayananTambahan : AppCompatActivity(), BayarContract {
    private lateinit var binding: ActivityLayananTambahanBinding
    private lateinit var presenter: BayarPresenter
    private lateinit var dataOrder: OrderModel
    private  val adapter: LayananTambahAdapter by lazy { LayananTambahAdapter () }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayananTambahanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = BayarPresenter(this, OrderAPI(), UserAPI()).apply {
            onAttach(this@LayananTambahan)
        }
        val order = intent.getParcelableExtra<OrderModel>("order")
        if (order != null) {
            dataOrder = order
        }
        DataPrice.priceList.clear()
        binding.rvPriceList.layoutManager = LinearLayoutManager(this)
        binding.rvPriceList.adapter = adapter
        val filteredList = DataPrice.priceList.filter { it.description_service.isNotEmpty() || it.price.isNotEmpty() }
        adapter.submitList(filteredList)

        binding.cvtLayanan.setOnClickListener {
            DataPrice.priceList.add(0, PriceModel("", "", ""))
            adapter.submitList(DataPrice.priceList)
        }

        binding.cvhLayanan.setOnClickListener {
            DataPrice.priceList.removeAt(0)
            adapter.submitList(DataPrice.priceList)
        }

        binding.cvPostHarga.setOnClickListener {
            DataPrice.priceList.forEach {
                presenter.postPrice("users/${DataToken.userId}/${dataOrder.id_service}", it.description_service, it.price)
            }
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSuccesPrice(price: PriceResponse?) {
        Log.d("Succes", "Success Post Harga")
    }

    override fun onErrorPrice(msg: String) {
        Log.d("error", "Error Post Harga")
    }

    override fun onSuccesGetPrice(price: PriceGetResponse?) {

    }

    override fun onErrorGetPrice(msg: String) {

    }

    override fun onSuccesuser(user: UserResponse?) {

    }

    override fun onError(msg: String) {

    }

    override fun onSuccesUpdate(status: StatusResponse?) {

    }
}
