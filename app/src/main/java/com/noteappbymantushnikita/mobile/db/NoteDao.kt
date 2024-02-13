package com.noteappbymantushnikita.mobile.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: NoteEntity)
    @Delete
    suspend fun deleteNote(note: NoteEntity)
    @Query("SELECT * FROM NoteEntity")
    suspend fun getAllNote(): List<NoteEntity>
}