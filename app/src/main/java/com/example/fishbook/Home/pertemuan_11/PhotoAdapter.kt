package com.example.fishbook.Home.pertemuan_11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fishbook.data.model.PhotoResponse
import com.example.fishbook.databinding.ItemPhotoBinding

class PhotoAdapter(private val listPhoto: List<PhotoResponse>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = listPhoto[position]
        holder.binding.tvAuthor.text = photo.author
        Glide.with(holder.itemView.context)
            .load(photo.downloadUrl)
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int = listPhoto.size
}
