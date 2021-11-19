package com.romero.notesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romero.notesapp.domain.GetQuotes
import com.romero.notesapp.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(): ViewModel() {

    val quotes = MutableLiveData<List<QuoteModel>>()

    var getQuotes = GetQuotes()

    fun getAllQuotes() {

        viewModelScope.launch(Dispatchers.IO) {

            val result = getQuotes()

            if(!result.isNullOrEmpty()) {
                quotes.postValue(result)

            }

        }

    }

}