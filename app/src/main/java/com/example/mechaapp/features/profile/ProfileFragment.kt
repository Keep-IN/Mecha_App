package com.example.mechaapp.features.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.viewbinding.ViewBindings
import com.example.mechaapp.R
import com.example.mechaapp.databinding.FragmentHistoryBinding
import com.example.mechaapp.databinding.FragmentProfileBinding
import com.example.mechaapp.features.Login.AlertDialogLoginSucces
import com.example.mechaapp.features.Login.ForgetPassword

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivEdit.setOnClickListener {
                startActivity(Intent(activity, EditProfile::class.java))
            }
            cvOut.setOnClickListener {
                AlertDialogKeluarAkun().show(childFragmentManager,"test")
            }
        }
    }



}