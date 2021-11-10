package com.romero.notesapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.romero.notesapp.R
import com.romero.notesapp.databinding.FragmentUpdateBinding
import com.romero.notesapp.model.Note
import com.romero.notesapp.viewmodel.NoteViewModel

class UpdateFragment : Fragment() {

    // FragmentUpdateBinding - UpdateFragment
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    // UpdateFragmentArgs - UpdateFragment
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        // NoteViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // if you need to understand the args.currentNote
        // see my_nav.xml argument and
        // see NotesAdapter.kt itemRowLayout.setOnClickListener
        binding.updateName.setText(args.currentNote.nameNote)

        // btn
        binding.btnUpdate.setOnClickListener {

            updateNoteToDatabase()

        }

        // add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateNoteToDatabase() {

        val updateName: String = binding.updateName.text.toString()

        if(inputCheck(updateName)) {

            val note = Note(args.currentNote.id, updateName, false)

            // Update Current Note - Database
            noteViewModel.updateNote(note)

            Toast.makeText(requireContext(), "update note!", Toast.LENGTH_LONG).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_notesFragment)

        } else {

            Toast.makeText(requireContext(), "Please fill out name field", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(updateName: String): Boolean {

        return !(TextUtils.isEmpty(updateName))

    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu) // delete_menu.xml

    }

    // menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete) { // selected the delete icon

            deleteNote()

        }

        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote() {

        val dialog = AlertDialog.Builder(requireContext())

        dialog.setPositiveButton("Yes") { _, _ ->

            // Delete Current Note - Database
            noteViewModel.deleteNote(args.currentNote)

            Toast.makeText(requireContext(), "delete note!", Toast.LENGTH_LONG).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_notesFragment)

        }

        dialog.setNegativeButton("No") { _, _ -> }

        dialog.setTitle("Delete note")
        dialog.setMessage("Are you sure you want to delete the note")
        dialog.create().show()

    }

}