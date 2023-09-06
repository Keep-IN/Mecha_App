package com.example.mechaapp.partner.home2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.FragmentHome2Binding
import com.example.mechaapp.data.adapter.HistoryListAdapter2
import com.example.mechaapp.partner.features2.ambilpesan.AmbilPesan
import com.example.mechaapp.data.Model.DataRiwayatMontir

class HomeFragment2 : Fragment() {
    private  lateinit var binding: FragmentHome2Binding
    private val adapterHistory: HistoryListAdapter2 by lazy { HistoryListAdapter2() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHome2Binding.inflate(layoutInflater, container, false)

        binding.cvambilPesan.setOnClickListener {
            startActivity(Intent(activity, AmbilPesan::class.java ))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        adapterHistory.submitList(DataRiwayatMontir.riwayathomeList)
        binding.rvMekanik.adapter = adapterHistory
        binding.rvMekanik.layoutManager = layoutManager
    }

}