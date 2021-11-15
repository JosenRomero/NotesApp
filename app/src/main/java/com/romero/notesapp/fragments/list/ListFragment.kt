package com.romero.notesapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.notesapp.R
import com.romero.notesapp.databinding.FragmentListBinding
import com.romero.notesapp.model.Quote

class ListFragment : Fragment() {

    // FragmentListBinding - ListFragment
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        // recyclerview
        binding.recyclerViewQuotes.adapter = adapter
        binding.recyclerViewQuotes.layoutManager = LinearLayoutManager(requireContext())

        // adding example data
        val test = ArrayList<Quote>()
        test.add(Quote("jose romero", "ejemplo 1 testing"))
        test.add(Quote("jose romero", "ejemplo 36 testing"))
        adapter.setQuotes(test)

        return binding.root
    }

}