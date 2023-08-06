package com.example.mechaapp.features.Map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityMapAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.GoogleMap

class MapAddress : AppCompatActivity() {
    private lateinit var binding: ActivityMapAddressBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMapAddressBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}