package com.example.mechaapp.features.Map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.mechaapp.R
import com.example.mechaapp.data.Model.DataAddress
import com.example.mechaapp.databinding.ActivityMapAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.Locale

class MapAddress : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapAddressBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var googleMap: GoogleMap
    private var layanan: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMapAddressBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        layanan = intent.getStringExtra("layanan").toString()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        binding.mvDevice.onCreate(savedInstanceState)
        binding.mvDevice.getMapAsync(this)

        requestLocationUpdates()
        binding.fabMyLoc.setOnClickListener {
            requestLocationUpdates()
        }
        binding.btnOrderNow.setOnClickListener {
            startActivity(Intent(this, ConfirmationOrder::class.java).apply {
                putExtra("alamat", DataAddress.address)
                putExtra("layanan", layanan)
            })
        }
    }
    override fun onStart() {
        super.onStart()
        binding.mvDevice.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mvDevice.onStop()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        binding.mvDevice.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mvDevice.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mvDevice.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mvDevice.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    private fun requestLocationUpdates() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val granted = permissions.all {
            ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        if (!granted) {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                // Handle location updates
                DataAddress.latitude = location.latitude
                DataAddress.longitude = location.longitude

                // Update the map with the current location
                val currentLocation = LatLng(DataAddress.latitude, DataAddress.longitude)
                val zoomLevel = 17f
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel)
                googleMap.animateCamera(cameraUpdate)
                binding.fabMyLoc.setOnClickListener {
                    googleMap.animateCamera(cameraUpdate)
                }

                DataAddress.address = getAddress(applicationContext, DataAddress.latitude, DataAddress.longitude)
                binding.tilLokasi.editText?.setText(DataAddress.address)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(currentLocation)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title("Current Location")
                )
                DataAddress.mapUrl = createMapUrl(DataAddress.longitude, DataAddress.latitude)
            }
        }
    }

    private fun getAddress(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>
        return try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1) as List<Address>
            if (addresses.isNotEmpty()) {
                addresses[0].getAddressLine(0) ?: ""
            } else {
                "No address found"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error fetching address"
        }
    }
    private fun createMapUrl(latitude: Double, longitude: Double,mapType: String = "roadmap"): String{
        //Create map URL
        val urlBuilder = StringBuilder("https://www.google.com/maps?q=")
        urlBuilder.append("$longitude, $latitude")
        if (DataAddress.address.isNotBlank()) {
            urlBuilder.append("&q=$longitude,$latitude(${DataAddress.address})")
        }
        if (mapType.isNotBlank()) {
            urlBuilder.append("&t=$mapType")
        }

        return urlBuilder.toString()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}