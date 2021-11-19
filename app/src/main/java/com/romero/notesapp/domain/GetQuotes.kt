package com.romero.notesapp.domain

import com.romero.notesapp.model.QuoteModel
import com.romero.notesapp.repository.QuoteRepository

class GetQuotes {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()

}