package com.noteappbymantushnikita.mobile.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun addNote(note: NoteEntity)
    @Delete
    fun deleteNote(note: NoteEntity)
    @Query("SELECT * FROM NoteEntity")
    fun getAllNote(): List<NoteEntity>
}