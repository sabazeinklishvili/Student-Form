package com.example.carorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lorem = getString(R.string.item_description_placeholder)

        val cars = listOf(
            Car("BMW M3 (F80 generation)", 38_000.0, R.drawable.car_bmw_m3_f80, lorem),
            Car("Mercedes-Benz CLS-Class (Third Generation)", 46_400.0, R.drawable.car_mercedes_cls_class, lorem),
            Car("Porsche 911 GT3 RS (991.1 Generation)", 189_000.0, R.drawable.car_porsche_911_gt3_rs, lorem),
            Car("Ferrari 488 Spider", 260_000.0, R.drawable.car_ferrari_488_spider, lorem)
        )

        findViewById<RecyclerView>(R.id.recyclerShop).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ShopAdapter(cars) { car ->
                val intent = Intent(this@MainActivity, OrderActivity::class.java).apply {
                    putExtra(IntentExtras.PRODUCT_NAME, car.name)
                    putExtra(IntentExtras.PRODUCT_PRICE, car.priceUsd)
                    putExtra(IntentExtras.PRODUCT_IMAGE_RES, car.imageResId)
                    putExtra(IntentExtras.PRODUCT_DESCRIPTION, car.description)
                }
                startActivity(intent)
            }
        }
    }
}
