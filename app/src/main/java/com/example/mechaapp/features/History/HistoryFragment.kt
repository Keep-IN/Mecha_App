package com.example.mechaapp.features.History

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Model.DataOrder
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.databinding.FragmentHistoryBinding
import com.example.mechaapp.features.DetailPesanan.DetailPesanan
import com.example.mechaapp.data.adapter.HistoryListAdapter

class HistoryFragment : Fragment(), HistoryContract {
    private lateinit var binding: FragmentHistoryBinding
    private val adapterHistory: HistoryListAdapter by lazy { HistoryListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvHistory.adapter = adapterHistory
        binding.rvHistory.layoutManager = layoutManager
        adapterHistory.setOnclickItem(rvClickListener)


        binding.cvSemuaHistory.setOnClickListener() {
            adapterHistory.submitList(DataOrder.orderList)
            binding.apply {
                cvSemuaHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                tvSemuaHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvProsesHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProsesHistory.setTextColor(Color.parseColor("#000000"))
                cvDibatalkanHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDibatalkanHistory.setTextColor(Color.parseColor("#000000"))
                cvSelesaiHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSelesaiHistory.setTextColor(Color.parseColor("#000000"))
                cvMenungguHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvMenungguHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvProsesHistory.setOnClickListener() {
            val filteredHistory = DataOrder.orderList.filter {
                it.status?.contains("Diproses") ?: false
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvSemuaHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSemuaHistory.setTextColor(Color.parseColor("#000000"))
                cvProsesHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                tvProsesHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvDibatalkanHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDibatalkanHistory.setTextColor(Color.parseColor("#000000"))
                cvSelesaiHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSelesaiHistory.setTextColor(Color.parseColor("#000000"))
                cvMenungguHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvMenungguHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvDibatalkanHistory.setOnClickListener() {
            val filteredHistory = DataOrder.orderList.filter {
                it.status?.contains("Dibatalkan") ?: false
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvSemuaHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSemuaHistory.setTextColor(Color.parseColor("#000000"))
                cvProsesHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProsesHistory.setTextColor(Color.parseColor("#000000"))
                cvDibatalkanHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                tvDibatalkanHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvSelesaiHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSelesaiHistory.setTextColor(Color.parseColor("#000000"))
                cvMenungguHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvMenungguHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvSelesaiHistory.setOnClickListener() {
            val filteredHistory = DataOrder.orderList.filter {
                it.status?.contains("Selesai") ?: false
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvSemuaHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSemuaHistory.setTextColor(Color.parseColor("#000000"))
                cvProsesHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProsesHistory.setTextColor(Color.parseColor("#000000"))
                cvDibatalkanHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDibatalkanHistory.setTextColor(Color.parseColor("#000000"))
                cvSelesaiHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                tvSelesaiHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvMenungguHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvMenungguHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvMenungguHistory.setOnClickListener() {
            val filteredHistory = DataOrder.orderList.filter {
                it.status?.contains("Selesai") ?: false
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvSemuaHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSemuaHistory.setTextColor(Color.parseColor("#000000"))
                cvProsesHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProsesHistory.setTextColor(Color.parseColor("#000000"))
                cvDibatalkanHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDibatalkanHistory.setTextColor(Color.parseColor("#000000"))
                cvSelesaiHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvSelesaiHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvMenungguHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                tvMenungguHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
    private val rvClickListener: (OrderModel) -> Unit =
        { item ->
            startActivity(Intent(activity, DetailPesanan::class.java))
        }

    override fun onSucces(history: OrderGetResponse?) {
        if (history != null) {
            adapterHistory.submitList(history.order)
        }
    }

    override fun onFailed(msg: String) {
        TODO("Not yet implemented")
    }
}