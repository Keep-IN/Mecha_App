package com.example.mechaapp.partner.home2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.FragmentHome2Binding
import com.example.mechaapp.partner.features2.adapter2.HistoryListAdapter2
import com.example.mechaapp.partner.features2.data2.DataRiwayat

class HomeFragment2 : Fragment() {
    private  lateinit var binding: FragmentHome2Binding
    private val adapterHistory: HistoryListAdapter2 by lazy { HistoryListAdapter2() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHome2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        adapterHistory.submitList(DataRiwayat.riwayathomeList)
        binding.rvMekanik.adapter = adapterHistory
        binding.rvMekanik.layoutManager = layoutManager
}
    }