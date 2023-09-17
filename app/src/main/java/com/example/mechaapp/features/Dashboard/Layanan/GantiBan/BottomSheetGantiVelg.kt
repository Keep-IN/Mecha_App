package com.example.mechaapp.features.Dashboard.Layanan.GantiBan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.mechaapp.databinding.BottomSheetGantiBanBinding
import com.example.mechaapp.databinding.BottomSheetVelgBinding
import com.example.mechaapp.features.Map.MapAddress

class BottomSheetGantiVelg: SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetVelgBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetVelgBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPesan.setOnClickListener {
            startActivity(Intent(activity, MapAddress::class.java).apply {
                putExtra("layanan", "Ganti Velg")
            })
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}