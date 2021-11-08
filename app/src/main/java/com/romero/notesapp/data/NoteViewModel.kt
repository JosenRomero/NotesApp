package com.romero.notesapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// A ViewModel acts as a communication center between the Repository and the UI

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {

        val noteDao = NoteDatabase.getDatabase(application).noteDao()

        repository = NoteRepository(noteDao)

        readAllNotes = repository.readAllNotes

    }

    fun addNote(note: Note) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.addNote(note)

        }

    }

}