package com.example.mechaapp.features.Loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.databinding.ActivityLoadingBinding
import com.example.mechaapp.features.Map.OrderContract
import com.example.mechaapp.features.Map.OrderPresenter

class Loading : AppCompatActivity(), OrderContract {
    private lateinit var binding: ActivityLoadingBinding
    private lateinit var  presenterOrder: OrderPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenterOrder = OrderPresenter(this, OrderAPI()).apply {
            onAttach(this@Loading)
        }
    }

    override fun onSuccesOrder(order: OrderResponse?) {
        TODO("Not yet implemented")
    }

    override fun onErrorOrder(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccesHistory(history: OrderResponse?) {
        TODO("Not yet implemented")
    }

    override fun onErrorhistory(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccesGetOrder(order: OrderGetResponse?) {
        TODO("Not yet implemented")
    }

    override fun onErrorgetOrder(msg: String) {
        TODO("Not yet implemented")
    }
}