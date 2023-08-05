package com.example.mechaapp.partner.home2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityNavbarContainer2Binding
import com.example.mechaapp.databinding.ActivityNavbarContainerBinding
import com.example.mechaapp.features.Dashboard.HomeFragment
import com.example.mechaapp.features.History.HistoryFragment
import com.example.mechaapp.features.chat.ChatFragment
import com.example.mechaapp.features.profile.ProfileFragment

class NavbarContainer2 : AppCompatActivity() {
    private lateinit var binding: ActivityNavbarContainer2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNavbarContainer2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(HomeFragment2())

        binding.bottomNavigationView2.selectedItemId = R.id.nav_home
        //binding.bottomNavigationView2.itemIconTintList = null
        binding.bottomNavigationView2.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment2())
                //    R.id.nav_chat -> replaceFragment(ChatFragment())
                //  R.id.nav_history -> replaceFragment(HistoryFragment())
                //R.id.nav_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.fragContainer2, fragment)
            commit()
        }
    }
}

