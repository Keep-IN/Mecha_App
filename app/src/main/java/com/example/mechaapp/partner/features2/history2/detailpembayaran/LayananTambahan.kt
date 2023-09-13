package com.example.mechaapp.partner.features2.history2.detailpembayaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.R
import com.example.mechaapp.data.Model.DataPrice
import com.example.mechaapp.data.Model.HargaItemModel
import com.example.mechaapp.data.adapter.LayananTambahAdapter
import com.example.mechaapp.data.adapter.SpotlightListAdapter
import com.example.mechaapp.databinding.ActivityLayananBanBinding
import com.example.mechaapp.databinding.ActivityLayananTambahanBinding
import com.example.mechaapp.databinding.LayananTambahanViewBinding


class LayananTambahan : AppCompatActivity() {
    private lateinit var binding: ActivityLayananTambahanBinding
    private lateinit var recyclerView: RecyclerView
    private  val adapter: LayananTambahAdapter by lazy { LayananTambahAdapter () }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayananTambahanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvPriceList.layoutManager = LinearLayoutManager(this)
        binding.rvPriceList.adapter = adapter
//        adapter.submitList(DataPrice.priceList)
//
//        binding.cvtLayanan.setOnClickListener {
//            DataPrice.priceList.add(0, HargaItemModel("", 0))
//            adapter.submitList(DataPrice.priceList)
//        }
//
//        binding.cvhLayanan.setOnClickListener {
//            DataPrice.priceList.removeAt(0)
//            adapter.submitList(DataPrice.priceList)
//        }
    }
}
