package com.example.fishbook.Home.pertemuan_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishbook.R

class FishAdapter(private val listFish: List<Fish>) : RecyclerView.Adapter<FishAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivFish: ImageView = view.findViewById(R.id.ivFish)
        val tvFishName: TextView = view.findViewById(R.id.tvFishName)
        val tvFishType: TextView = view.findViewById(R.id.tvFishType)
        val tvFishPrice: TextView = view.findViewById(R.id.tvFishPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fish = listFish[position]
        holder.tvFishName.text = fish.name
        holder.tvFishType.text = fish.type
        holder.tvFishPrice.text = fish.price
        holder.ivFish.setImageResource(fish.imageResId)
    }

    override fun getItemCount(): Int = listFish.size
}
