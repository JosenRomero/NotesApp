package com.romero.notesapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romero.notesapp.databinding.ItemRowListBinding
import com.romero.notesapp.model.Quote

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var quotesList = emptyList<Quote>()

    // // R.layout.item_row_list - ItemRowListBinding
    class ListViewHolder(val binding: ItemRowListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.binding.apply {

            textContent.text = quotesList[position].content
            textAuthor.text = quotesList[position].author

        }

    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    fun setQuotes(quotes: List<Quote>) {

        quotesList = quotes

        notifyDataSetChanged()

    }

}