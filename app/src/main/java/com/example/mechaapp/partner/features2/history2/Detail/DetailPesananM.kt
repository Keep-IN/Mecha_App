package com.example.mechaapp.partner.features2.history2.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.adapter.DetailMontirAdapter
import com.example.mechaapp.databinding.ActivityDetailPesananMontirBinding

class DetailPesananM : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPesananMontirBinding
    private lateinit var presenter: DetailPresenter
    private val adapterDetailmontir: DetailMontirAdapter by lazy { DetailMontirAdapter () }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananMontirBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this)

    }
}