package com.romero.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nameNote: String,
    val finishNote: Boolean
)
