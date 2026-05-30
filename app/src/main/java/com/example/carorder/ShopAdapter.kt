package com.example.carorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale


class ShopAdapter(
    private val cars: List<Car>,
    private val onCarClick: (Car) -> Unit
) : RecyclerView.Adapter<ShopAdapter.VH>() {

    private val currencyUs: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shop_card, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val car = cars[position]
        holder.image.setImageResource(car.imageResId)
        holder.title.text = car.name
        holder.price.text = currencyUs.format(car.priceUsd)
        holder.itemView.setOnClickListener { onCarClick(car) }
    }

    override fun getItemCount(): Int = cars.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageCar)
        val title: TextView = itemView.findViewById(R.id.textTitle)
        val price: TextView = itemView.findViewById(R.id.textPrice)
    }
}
