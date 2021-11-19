package com.romero.notesapp.repository

import com.romero.notesapp.api.QuoteService
import com.romero.notesapp.model.QuoteModel

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {

        val response = api.getAllQuotes()

        return response

    }
}