package com.romero.notesapp.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("q")val quote: String,
    @SerializedName("a")val author: String,
    @SerializedName("c")val c: String,
    @SerializedName("h")val h: String
)