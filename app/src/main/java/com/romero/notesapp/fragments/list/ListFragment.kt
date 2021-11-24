package com.romero.notesapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.notesapp.databinding.FragmentListBinding
import com.romero.notesapp.viewmodel.QuoteViewModel

class ListFragment : Fragment() {

    // FragmentListBinding - ListFragment
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var adapter = ListAdapter()

    private var quoteViewModel: QuoteViewModel = QuoteViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        // recyclerview
        binding.recyclerViewQuotes.adapter = adapter
        binding.recyclerViewQuotes.layoutManager = LinearLayoutManager(requireContext())

        // quoteViewModel
        quoteViewModel.getAllQuotes()
        quoteViewModel.quotes.observe(viewLifecycleOwner, Observer { data ->

            adapter.setQuotes(data)

        })

        // progress bar
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {

            binding.progressBar.isVisible = it

        })

        return binding.root
    }

}