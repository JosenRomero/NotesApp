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

    // hide/show progress bar
    var isLoading = MutableLiveData<Boolean>()

    fun getAllQuotes() {

        viewModelScope.launch(Dispatchers.IO) {

            // show progress bar
            isLoading.postValue(true)

            val result = getQuotes()

            if(!result.isNullOrEmpty()) {

                quotes.postValue(result)

                // hide progress bar
                isLoading.postValue(false)

            }

        }

    }

}