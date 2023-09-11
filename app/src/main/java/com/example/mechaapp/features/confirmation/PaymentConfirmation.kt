package com.example.mechaapp.features.confirmation

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.mechaapp.R
import com.example.mechaapp.databinding.ActivityPaymentConfirmationBinding

class PaymentConfirmation : AppCompatActivity() {
    private lateinit var binding:ActivityPaymentConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPaymentConfirmationBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}