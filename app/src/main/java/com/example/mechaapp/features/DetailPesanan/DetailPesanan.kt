package com.example.mechaapp.features.DetailPesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.R
import com.example.mechaapp.data.adapter.DetailMontirAdapter
import com.example.mechaapp.databinding.ActivityDetailPesananBinding
import com.example.mechaapp.databinding.ActivityMainBinding
import com.example.mechaapp.features.History.HistoryFragment
import com.example.mechaapp.features.Login.ForgetPassword

class DetailPesanan : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPesananBinding
    private val adapterDetailuser: DetailMontirAdapter by lazy { DetailMontirAdapter () }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesananBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)


        val layoutManager = LinearLayoutManager(this)
        //adapterDetailmontir.submitList(DataPembayaran.metodeList)
        binding.rvDetailuser.adapter = adapterDetailuser
        binding.rvDetailuser.layoutManager = layoutManager

        binding.cvMetode.setOnClickListener {
            startActivity(Intent(this, MetodePembayaran::class.java ))
        }
        binding.ivBackdetail.setOnClickListener {
            startActivity(Intent(this, HistoryFragment::class.java ))
        }
    }
}