package com.romero.notesapp.fragments.notes

import android.graphics.Paint
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

            // checkbox

            checkBox.isChecked = notesList[position].finishNote

            checkBox.setOnClickListener {

                // method is in MyClickListener interface
                listener.onCheckBoxClick(notesList[position].id, notesList[position].nameNote, notesList[position].finishNote)

            }

            // if checkbox is Checked then strikethrough text
            nameNoteTxt.paintFlags = strikethrough(checkBox.isChecked)

            checkBox.setOnCheckedChangeListener { _, isChecked ->

                // if checkbox is Checked then strikethrough text
                nameNoteTxt.paintFlags = strikethrough(isChecked)

            }

            // itemRowLayout

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

    private fun strikethrough(isChecked: Boolean): Int {

        // Paint.STRIKE_THRU_TEXT_FLAG strikethrough text
        // Paint.ANTI_ALIAS_FLAG normal text
        return if (isChecked) Paint.STRIKE_THRU_TEXT_FLAG else Paint.ANTI_ALIAS_FLAG

    }

}