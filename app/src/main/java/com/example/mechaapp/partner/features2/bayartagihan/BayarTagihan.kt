package com.example.mechaapp.partner.features2.bayartagihan

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mechaapp.R

class BayarTagihan : AppCompatActivity() {
    private lateinit var textViewSource: TextView
    private lateinit var textViewCopyButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_tagihan)

        textViewSource = findViewById(R.id.tvsourceCopy)
        textViewCopyButton = findViewById(R.id.tvCopy)

        // Menambahkan OnClickListener pada textViewCopyButton
        textViewCopyButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Mengambil teks dari textViewSource
                val textToCopy = textViewSource.text.toString()

                // Mengakses ClipboardManager
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // Membuat ClipData dengan teks yang akan disalin
                val clipData = ClipData.newPlainText("Teks yang disalin", textToCopy)

                // Menyalin teks ke clipboard
                clipboardManager.setPrimaryClip(clipData)

                // Menampilkan pesan jika teks berhasil disalin
                Toast.makeText(
                    this@BayarTagihan,
                    "Teks berhasil disalin ke clipboard",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    }
