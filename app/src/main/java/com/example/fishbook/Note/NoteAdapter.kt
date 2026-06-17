package com.example.fishbook.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fishbook.data.entity.NoteEntity
import com.example.fishbook.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(
    private var notes: List<NoteEntity>,
    private val onDeleteClick: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content
        
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        holder.binding.tvDate.text = sdf.format(Date(note.createdAt))

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(note)
        }
    }

    override fun getItemCount(): Int = notes.size

    fun updateData(newNotes: List<NoteEntity>) {
        this.notes = newNotes
        notifyDataSetChanged()
    }
}
