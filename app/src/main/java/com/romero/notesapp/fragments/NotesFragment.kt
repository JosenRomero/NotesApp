package com.romero.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.romero.notesapp.R
import com.romero.notesapp.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    // FragmentNotesBinding - NotesFragment
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        // btn
        binding.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_notesFragment_to_addFragment)

        }

        return binding.root
    }

}