package com.example.mechaapp.features.Dashboard.Layanan.GantiBan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.mechaapp.databinding.BottomSheetGantiBanBinding
import com.example.mechaapp.databinding.BottomSheetVelgBinding

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
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}