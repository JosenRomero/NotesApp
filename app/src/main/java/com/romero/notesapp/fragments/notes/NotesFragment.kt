package com.romero.notesapp.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.notesapp.R
import com.romero.notesapp.data.NoteViewModel
import com.romero.notesapp.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    // FragmentNotesBinding - NotesFragment
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    private var adapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        // recyclerview
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

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

        return binding.root
    }

}