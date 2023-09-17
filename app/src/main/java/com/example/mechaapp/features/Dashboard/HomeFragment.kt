package com.example.mechaapp.features.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.FragmentHomeBinding
import com.example.mechaapp.data.adapter.SpotlightListAdapter
import com.example.mechaapp.data.Model.DataSpotlight
import com.example.mechaapp.data.Model.DataUser
import com.example.mechaapp.features.Dashboard.Layanan.Emergency.LayananDarurat
import com.example.mechaapp.features.Dashboard.Layanan.GantiBan.LayananBan
import com.example.mechaapp.features.Dashboard.Layanan.Service.LayananPerbaikan

class HomeFragment : Fragment() {
    private  lateinit var binding: FragmentHomeBinding
    private val adapterSpotlight: SpotlightListAdapter by lazy { SpotlightListAdapter () }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerHorizontal = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        adapterSpotlight.submitList(DataSpotlight.spotlightList)
        binding.rvSpotlight.adapter = adapterSpotlight
        binding.rvSpotlight.layoutManager = layoutManagerHorizontal
        binding.tvnamaDashboard.text = DataUser.nama

        binding.cvTireFix.setOnClickListener {
            startActivity(Intent(activity, LayananBan::class.java))
        }
        binding.cvLayananDarurat.setOnClickListener {
            startActivity(Intent(activity, LayananDarurat::class.java))
        }
        binding.cvRawatKendaraan.setOnClickListener {
            startActivity(Intent(activity, LayananPerbaikan::class.java))
        }
    }

}