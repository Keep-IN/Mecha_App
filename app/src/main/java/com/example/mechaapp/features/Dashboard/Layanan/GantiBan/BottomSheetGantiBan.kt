package com.example.mechaapp.features.Dashboard.Layanan.GantiBan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.mechaapp.databinding.BottomSheetGantiBanBinding
import com.example.mechaapp.features.Map.MapAddress

class BottomSheetGantiBan: SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetGantiBanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetGantiBanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPesan.setOnClickListener {
            startActivity(Intent(activity, MapAddress::class.java))
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}