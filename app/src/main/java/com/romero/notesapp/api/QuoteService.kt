package com.romero.notesapp.api

import com.romero.notesapp.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllQuotes(): List<QuoteModel> {

        // coroutine
        return withContext(Dispatchers.IO) {

            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()

            // if response.body() is null then return emptyList
            response.body() ?: emptyList()

        }

    }

}