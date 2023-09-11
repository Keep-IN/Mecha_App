package com.example.mechaapp.features.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.R
import com.example.mechaapp.data.Model.DataSpotlight
import com.example.mechaapp.databinding.ActivityAlertDialogKeluarAkunBinding
import com.example.mechaapp.databinding.AlertDialogLoginBinding
import com.example.mechaapp.features.Dashboard.Layanan.GantiBan.LayananBan
import com.example.mechaapp.features.OnBoard.LandingPage

class AlertDialogKeluarAkun : DialogFragment() {
    lateinit var binding: ActivityAlertDialogKeluarAkunBinding

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityAlertDialogKeluarAkunBinding.inflate(layoutInflater, container, false)

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerHorizontal = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.cvoutAkun.setOnClickListener {
            startActivity(Intent(activity, LandingPage::class.java))
        }
    }

}