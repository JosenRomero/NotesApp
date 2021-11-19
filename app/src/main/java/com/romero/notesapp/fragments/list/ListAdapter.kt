package com.romero.notesapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romero.notesapp.databinding.ItemRowListBinding
import com.romero.notesapp.model.QuoteModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var quotes = emptyList<QuoteModel>()

    // R.layout.item_row_list - ItemRowListBinding
    class ListViewHolder(val binding: ItemRowListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.binding.apply {

            textContent.text = quotes[position].quote
            textAuthor.text = quotes[position].author

        }

    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    fun setQuotes(quotesList: List<QuoteModel>) {

        quotes = quotesList

        notifyDataSetChanged()

    }

}