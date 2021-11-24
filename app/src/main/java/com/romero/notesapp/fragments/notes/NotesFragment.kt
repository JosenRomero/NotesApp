package com.romero.notesapp.fragments.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.notesapp.R
import com.romero.notesapp.viewmodel.NoteViewModel
import com.romero.notesapp.databinding.FragmentNotesBinding
import com.romero.notesapp.model.Note

class NotesFragment : Fragment(), NoteAdapter.MyClickListener {

    // FragmentNotesBinding - NotesFragment
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    private var adapter = NoteAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        // recyclerview
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        // NoteViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Read Database
        noteViewModel.readAllNotes.observe(viewLifecycleOwner, Observer { note ->

            adapter.setNotes(note)

        })

        // btn
        binding.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_notesFragment_to_addFragment)

        }

        // add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu) // delete_menu.xml

    }

    // menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete) { // selected the delete icon

            deleteAllNotes()

        }

        return super.onOptionsItemSelected(item)

    }

    private fun deleteAllNotes() {

        val dialog = AlertDialog.Builder(requireContext())

        dialog.setPositiveButton("Yes") {_, _ ->

            // Delete all notes - Database
            noteViewModel.deleteAllNotes()

            Toast.makeText(requireContext(), "delete all notes!", Toast.LENGTH_LONG).show()

        }

        dialog.setNegativeButton("No") {_, _ -> }

        dialog.setTitle("Delete all notes")
        dialog.setMessage("Are you sure you want to delete all notes")
        dialog.create().show()

    }

    // see NoteAdapter.kt
    // this method needs NoteAdapter.MyClickListener
    override fun onCheckBoxClick(position: Int, nameNote: String, finishNote: Boolean) {

        val finishN = !finishNote

        val note = Note(position, nameNote, finishN)

        // Update Current Note - Database
        noteViewModel.updateNote(note)

    }

}