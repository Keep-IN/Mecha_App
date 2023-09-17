package com.example.mechaapp.features.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityNavbarContainerBinding
import com.example.mechaapp.features.BottomSheetforAll.BottomSheetKeluar
import com.example.mechaapp.features.History.HistoryFragment
import com.example.mechaapp.features.chat.ChatFragment
import com.example.mechaapp.features.profile.ProfileFragment

class NavbarContainer : AppCompatActivity() {
    private lateinit var binding: ActivityNavbarContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNavbarContainerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.selectedItemId = R.id.nav_home
        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_chat -> replaceFragment(ChatFragment())
                R.id.nav_history -> replaceFragment(HistoryFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.fragContainer, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        BottomSheetKeluar().show(supportFragmentManager, "Keluar")
    }
}