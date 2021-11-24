package com.romero.notesapp.fragments.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.romero.notesapp.model.Note
import com.romero.notesapp.databinding.ItemRowBinding

class NoteAdapter(private val listener: MyClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notesList = emptyList<Note>()

    interface MyClickListener {

        fun onCheckBoxClick(position: Int, nameNote: String, finishNote: Boolean)

    }

    // R.layout.item_row - ItemRowBinding
    class NoteViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NoteViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = notesList[position]

        holder.binding.apply {

            idTxt.text = position.toString()
            nameNoteTxt.text = notesList[position].nameNote
            checkBox.isChecked = notesList[position].finishNote

            itemRowLayout.setOnClickListener {

                val action = NotesFragmentDirections.actionNotesFragmentToUpdateFragment(currentNote)

                holder.itemView.findNavController().navigate(action)

            }

        }

        holder.binding.checkBox.setOnClickListener {

            // method is in MyClickListener interface
            listener.onCheckBoxClick(notesList[position].id, notesList[position].nameNote, notesList[position].finishNote)

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