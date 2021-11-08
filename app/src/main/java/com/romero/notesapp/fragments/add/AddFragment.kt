package com.romero.notesapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.romero.notesapp.R
import com.romero.notesapp.data.Note
import com.romero.notesapp.data.NoteViewModel
import com.romero.notesapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    // FragmentAddBinding - AddFragment
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        // NoteViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // btn
        binding.btnAdd.setOnClickListener {

            addNoteToDatabase()

        }

        return binding.root
    }

    private fun addNoteToDatabase() {

        val nameNote: String = binding.nameNote.text.toString()

        if(inputCheck(nameNote)) {

            val newNote = Note(0, nameNote, false)

            // Add Note to Database
            noteViewModel.addNote(newNote)

            Toast.makeText(requireContext(), "new note!", Toast.LENGTH_LONG).show()

            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_notesFragment)

        } else {

            Toast.makeText(requireContext(), "Please fill out name field", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(nameNote: String): Boolean {

        return !(TextUtils.isEmpty(nameNote))

    }

}