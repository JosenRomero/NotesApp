package com.romero.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.romero.notesapp.model.Note

// DAO - Data Access Object

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllNotes(): LiveData<List<Note>>

}