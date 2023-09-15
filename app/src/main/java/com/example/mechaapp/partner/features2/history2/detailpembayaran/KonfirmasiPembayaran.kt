package com.example.mechaapp.partner.features2.history2.detailpembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityDetailPembayaranBinding
import com.example.mechaapp.databinding.ActivityKonfirmasiPembayaranBinding
import com.example.mechaapp.databinding.AlertBayarSuksesBinding
import com.example.mechaapp.databinding.FragmentProfileBinding
import com.example.mechaapp.features.profile.AlertDialogKeluarAkun
import com.example.mechaapp.features.profile.EditProfile
import com.example.mechaapp.partner.features2.History2.detailpembayaran.AlertBayarSukses

class KonfirmasiPembayaran : AppCompatActivity() {

    private lateinit var binding: ActivityKonfirmasiPembayaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKonfirmasiPembayaranBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            cvKonfirmasi.setOnClickListener {
                AlertDialogKeluarAkun()
            }
        }
    }
}