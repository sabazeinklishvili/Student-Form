package com.example.carorder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.NumberFormat
import java.util.Locale


class OrderActivity : AppCompatActivity() {

    private var basePriceUsd: Double = 0.0
    private lateinit var textTotal: TextView
    private lateinit var radioGroupDelivery: RadioGroup

    private val currencyUs: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val incoming = intent
        val productName = incoming.getStringExtra(IntentExtras.PRODUCT_NAME)
        basePriceUsd = incoming.getDoubleExtra(IntentExtras.PRODUCT_PRICE, 0.0)
        val imageRes = incoming.getIntExtra(IntentExtras.PRODUCT_IMAGE_RES, 0)
        val description = incoming.getStringExtra(IntentExtras.PRODUCT_DESCRIPTION)

        findViewById<TextView>(R.id.textProductName).text = productName.orEmpty()
        findViewById<TextView>(R.id.textProductDescription).text = description.orEmpty()
        findViewById<TextView>(R.id.textListPrice).text = currencyUs.format(basePriceUsd)
        if (imageRes != 0) {
            findViewById<ImageView>(R.id.imageProduct).setImageResource(imageRes)
        }

        textTotal = findViewById(R.id.textTotal)
        radioGroupDelivery = findViewById(R.id.radioGroupDelivery)

        radioGroupDelivery.setOnCheckedChangeListener { _, _ -> refreshTotal() }
        refreshTotal()

        findViewById<MaterialButton>(R.id.buttonPay).setOnClickListener {
            startActivity(Intent(this, SuccessActivity::class.java))
        }
    }

    /** Grand total: (price ? 5%) + (Express ? $1,700 : $0) */
    private fun computeTotalUsd(): Double {
        val afterDiscount = basePriceUsd * (1.0 - DISCOUNT_RATE)
        val express = radioGroupDelivery.checkedRadioButtonId == R.id.radioExpress
        val deliveryExtra = if (express) EXPRESS_SURCHARGE_USD else 0.0
        return afterDiscount + deliveryExtra
    }

    private fun refreshTotal() {
        textTotal.text = currencyUs.format(computeTotalUsd())
    }

    companion object {
        private const val DISCOUNT_RATE = 0.05
        private const val EXPRESS_SURCHARGE_USD = 1_700.0
    }
}
