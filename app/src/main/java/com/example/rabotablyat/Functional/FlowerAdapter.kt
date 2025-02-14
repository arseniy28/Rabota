package com.example.rabotablyat.Functional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rabotablyat.R
import com.example.rabotablyat.models.Flower

class FlowerAdapter(private val flowerList: List<Flower>) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flowerName: TextView = itemView.findViewById(R.id.titleTextView)
        // Добавьте другие элементы, если необходимо
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flower, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = flowerList[position]
        holder.flowerName.text = flower.name // Предполагается, что у вас есть поле name в классе Flower
        // Установите другие данные, если необходимо
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }
}