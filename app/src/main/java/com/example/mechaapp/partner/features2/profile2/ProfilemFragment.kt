package com.example.mechaapp.partner.features2.profile2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mechaapp.databinding.FragmentProfileBinding
import com.example.mechaapp.databinding.FragmentProfilemBinding
import com.example.mechaapp.features.profile.AlertDialogKeluarAkun
import com.example.mechaapp.features.profile.EditProfile

class ProfilemFragment : Fragment() {
    private lateinit var binding: FragmentProfilemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivEditm.setOnClickListener {
                startActivity(Intent(activity, EditProfilem::class.java))
            }
            cvOutm.setOnClickListener {
                AlertDialogKeluarAkun().show(childFragmentManager,"test")
            }
        }
    }

}