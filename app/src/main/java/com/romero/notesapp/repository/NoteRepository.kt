package com.romero.notesapp.repository

import androidx.lifecycle.LiveData
import com.romero.notesapp.data.NoteDao
import com.romero.notesapp.model.Note

// A repository class access to multiple data sources

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

    suspend fun addNote(note: Note) {

        noteDao.addNote(note)

    }

    suspend fun updateNote(note: Note) {

        noteDao.updateNote(note)

    }


}