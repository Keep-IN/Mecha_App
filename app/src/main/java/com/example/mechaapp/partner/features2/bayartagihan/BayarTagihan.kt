package com.example.mechaapp.partner.features2.bayartagihan

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.example.mechaapp.databinding.ActivityBayarTagihanBinding
import com.example.mechaapp.features.History.HistoryFragment
import com.example.mechaapp.partner.features2.history2.detailpembayaran.AlertBayarSukses
import com.example.mechaapp.partner.home2.HomeFragment2

class BayarTagihan : AppCompatActivity() {
    private lateinit var binding: ActivityBayarTagihanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBayarTagihanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val tagihan = intent.getStringExtra("tagihan")
        binding.tvTotalTagihan.text = tagihan

        binding.tvCopy.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val textToCopy = binding.tvsourceCopy.text.toString()
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Teks yang disalin", textToCopy)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(
                    this@BayarTagihan,
                    "Teks berhasil disalin ke clipboard",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding.ivbTag.setOnClickListener {
            startActivity(Intent(this, HomeFragment2::class.java ))
        }

        binding.cvSudah.setOnClickListener {
            AlertBayarSukses().show(supportFragmentManager, "Sukses")
            Handler(Looper.getMainLooper()).postDelayed({
                onBackPressedDispatcher.onBackPressed()
            },2000)
        }
    }
}
