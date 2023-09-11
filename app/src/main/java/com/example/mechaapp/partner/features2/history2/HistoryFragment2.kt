package com.example.mechaapp.partner.features2.history2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.R
import com.example.mechaapp.databinding.FragmentHistory2Binding
import com.example.mechaapp.partner.features2.adapter2.HistoryFragmentAdapter
import com.example.mechaapp.partner.features2.data2.DataRiwayatFragment
import com.example.mechaapp.partner.features2.data2.RiwayatFragmentItemModel

class HistoryFragment2 : Fragment() {
    private lateinit var binding: FragmentHistory2Binding
    private val adapterHistory: HistoryFragmentAdapter by lazy {HistoryFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistory2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        adapterHistory.submitList(DataRiwayatFragment.riwayatfragmentList)
        binding.rvhistoryFragment.adapter = adapterHistory
        binding.rvhistoryFragment.layoutManager = layoutManager


        binding.cvAllHistory.setOnClickListener() {
            adapterHistory.submitList(DataRiwayatFragment.riwayatfragmentList)
            binding.apply {
                cvAllHistory.setCardBackgroundColor(Color.parseColor("#56AB91"))
                tvAllHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvDoneHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDoneHistory.setTextColor(Color.parseColor("#000000"))
                cvProcessHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProcessHistory.setTextColor(Color.parseColor("#000000"))
                cvDeclineHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDeclineHistory.setTextColor(Color.parseColor("#000000"))
                cvWaitingHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvWaitingHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvDoneHistory.setOnClickListener() {
            val filteredHistory = DataRiwayatFragment.riwayatfragmentList.filter {
                it.status.contains("Selesai")
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvAllHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvAllHistory.setTextColor(Color.parseColor("#000000"))
                cvDoneHistory.setCardBackgroundColor(Color.parseColor("#56AB91"))
                tvDoneHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvProcessHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProcessHistory.setTextColor(Color.parseColor("#000000"))
                cvDeclineHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDeclineHistory.setTextColor(Color.parseColor("#000000"))
                cvWaitingHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvWaitingHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvProcessHistory.setOnClickListener() {
            val filteredHistory = DataRiwayatFragment.riwayatfragmentList.filter {
                it.status.contains("Dibatalkan")
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvAllHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvAllHistory.setTextColor(Color.parseColor("#000000"))
                cvDoneHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDoneHistory.setTextColor(Color.parseColor("#000000"))
                cvProcessHistory.setCardBackgroundColor(Color.parseColor("#56AB91"))
                tvProcessHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvDeclineHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDeclineHistory.setTextColor(Color.parseColor("#000000"))
                cvWaitingHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvWaitingHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvDeclineHistory.setOnClickListener() {
            val filteredHistory = DataRiwayatFragment.riwayatfragmentList.filter {
                it.status.contains("Selesai")
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvAllHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvAllHistory.setTextColor(Color.parseColor("#000000"))
                cvDoneHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDoneHistory.setTextColor(Color.parseColor("#000000"))
                cvProcessHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProcessHistory.setTextColor(Color.parseColor("#000000"))
                cvDeclineHistory.setCardBackgroundColor(Color.parseColor("#56AB91"))
                tvDeclineHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvWaitingHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvWaitingHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvWaitingHistory.setOnClickListener() {
            val filteredHistory = DataRiwayatFragment.riwayatfragmentList.filter {
                it.status.contains("Selesai")
            }
            adapterHistory.submitList(filteredHistory)
            binding.apply {
                cvAllHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvAllHistory.setTextColor(Color.parseColor("#000000"))
                cvDoneHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDoneHistory.setTextColor(Color.parseColor("#000000"))
                cvProcessHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvProcessHistory.setTextColor(Color.parseColor("#000000"))
                cvDeclineHistory.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                tvDeclineHistory.setTextColor(Color.parseColor("#FFFFFF"))
                cvWaitingHistory.setCardBackgroundColor(Color.parseColor("#56AB91"))
                tvWaitingHistory.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}