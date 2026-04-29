package com.example.fishbook.Home.pertemuan_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fishbook.R

class GameAdapter(
    private var listGame: MutableList<GameProduct>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivGame: ImageView = view.findViewById(R.id.ivGame)
        val tvGameName: TextView = view.findViewById(R.id.tvGameName)
        val tvPriceRange: TextView = view.findViewById(R.id.tvPriceRange)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = listGame[position]
        holder.tvGameName.text = game.name
        holder.tvPriceRange.text = game.priceRange
        
        Glide.with(holder.itemView.context)
            .load(game.imageUrl)
            .placeholder(R.drawable.ic_controller)
            .into(holder.ivGame)

        holder.btnDelete.setOnClickListener {
            onDeleteClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = listGame.size
}
