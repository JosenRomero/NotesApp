package com.romero.notesapp.fragments.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romero.notesapp.data.Note
import com.romero.notesapp.databinding.ItemRowBinding

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notesList = emptyList<Note>()

    // R.layout.item_row - ItemRowBinding
    class NoteViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NoteViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.binding.apply {

            idTxt.text = notesList[position].id.toString()
            nameNoteTxt.text = notesList[position].nameNote

        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(notes: List<Note>) {

        notesList = notes

        notifyDataSetChanged()

    }

}