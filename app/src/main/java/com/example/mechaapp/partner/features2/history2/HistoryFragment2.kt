package com.example.mechaapp.partner.features2.history2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.R
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.databinding.FragmentHistory2Binding
import com.example.mechaapp.partner.features2.adapter2.HistoryFragmentAdapter
import com.example.mechaapp.partner.features2.data2.DataRiwayatFragment
import com.example.mechaapp.partner.features2.data2.RiwayatFragmentItemModel

class HistoryFragment2 : Fragment(), HistoryContract2 {
    private lateinit var binding: FragmentHistory2Binding
    private val adapterHistory: HistoryFragmentAdapter by lazy {HistoryFragmentAdapter() }
    private lateinit var presenter: HistoryPresenter2
    private lateinit var dataHistory: List<OrderModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistory2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HistoryPresenter2(this, OrderAPI()).apply {
            onAttach(this@HistoryFragment2)
        }

        val layoutManager = LinearLayoutManager(activity)
        binding.rvhistoryFragment.adapter = adapterHistory
        binding.rvhistoryFragment.layoutManager = layoutManager
        presenter.getHistory(DataToken.idUser)



        binding.cvAllHistory.setOnClickListener() {
            adapterHistory.submitList(dataHistory)
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
            val filteredHistory = dataHistory.filter {
                it.status?.contains("Selesai") ?: false
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
            val filteredHistory = dataHistory.filter {
                it.status?.contains("Dibatalkan") ?: false
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
            val filteredHistory = dataHistory.filter {
                it.status?.contains("Selesai") ?: false
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
            val filteredHistory = dataHistory.filter {
                it.status?.contains("Selesai") ?: false
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

    override fun onSucces(history: HistoryGetResponse?) {
        if (history != null) {
            adapterHistory.submitList(history.history)
            dataHistory = history.history
        }
    }

    override fun onFailed(msg: String) {
        Toast.makeText(activity, "gagal", Toast.LENGTH_SHORT).show()
    }
}