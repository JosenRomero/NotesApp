package com.romero.notesapp.fragments.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.romero.notesapp.model.Note
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

        val currentNote = notesList[position]

        holder.binding.apply {

            idTxt.text = notesList[position].id.toString()
            nameNoteTxt.text = notesList[position].nameNote

            itemRowLayout.setOnClickListener {

                val action = NotesFragmentDirections.actionNotesFragmentToUpdateFragment(currentNote)

                holder.itemView.findNavController().navigate(action)

            }

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