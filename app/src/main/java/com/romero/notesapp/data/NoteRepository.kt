package com.romero.notesapp.data

import androidx.lifecycle.LiveData

// A repository class access to multiple data sources

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

    suspend fun addNote(note: Note) {

        noteDao.addNote(note)

    }


}