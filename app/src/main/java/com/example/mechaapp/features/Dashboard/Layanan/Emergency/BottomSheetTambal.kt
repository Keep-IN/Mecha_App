package com.example.mechaapp.features.Dashboard.Layanan.Emergency

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.mechaapp.databinding.BottomSheetEmergencyBinding
import com.example.mechaapp.databinding.BottomSheetTambalBinding
import com.example.mechaapp.features.Map.MapAddress

class BottomSheetTambal: SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetTambalBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetTambalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPesan.setOnClickListener {
            startActivity(Intent(activity, MapAddress::class.java).apply {
                putExtra("layanan", "Tambal Ban")
            })
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}