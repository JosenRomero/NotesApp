package com.romero.notesapp.api

import com.romero.notesapp.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET("api/quotes")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

}
