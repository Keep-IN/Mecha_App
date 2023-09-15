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


        textViewCopyButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val textToCopy = textViewSource.text.toString()
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Teks yang disalin", textToCopy)

                clipboardManager.setPrimaryClip(clipData)

                Toast.makeText(
                    this@BayarTagihan,
                    "Teks berhasil disalin ke clipboard",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    }
