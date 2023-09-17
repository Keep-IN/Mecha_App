package com.example.mechaapp.features.Dashboard.Layanan.Service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.mechaapp.databinding.BottomSheetGantiOliBinding
import com.example.mechaapp.databinding.BottomSheetInjeksiBinding
import com.example.mechaapp.features.Map.MapAddress

class BottomSheetInjeksi : SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetInjeksiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetInjeksiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPesan.setOnClickListener {
            startActivity(Intent(activity, MapAddress::class.java).apply {
                putExtra("layanan", "Injeksi Motor")
            })
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}