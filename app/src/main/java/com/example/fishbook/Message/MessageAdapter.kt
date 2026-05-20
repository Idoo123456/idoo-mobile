package com.example.fishbook.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.fishbook.R
import com.example.fishbook.databinding.ItemMessageBinding

class MessageAdapter(
    context: Context,
    private val Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding = if (convertView == null) {
            ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemMessageBinding.bind(convertView)
        }
        val view = binding.root

        val data = getItem(position) ?: return view

        // Load gambar menggunakan Glide dengan placeholder
        Glide.with(context)
            .load(data.avatarUrl)
            .circleCrop()
            .placeholder(android.R.drawable.ic_menu_gallery) // Gambar saat loading
            .error(android.R.drawable.ic_menu_report_image) // Gambar jika gagal
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText
        binding.textTime.text = data.time

        return view
    }
}
