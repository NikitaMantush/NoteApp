package com.noteappbymantushnikita.mobile.repository

import com.noteappbymantushnikita.mobile.db.NoteDao
import com.noteappbymantushnikita.mobile.db.NoteEntity
import com.noteappbymantushnikita.mobile.model.Note
import java.util.Date
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    suspend fun getNoteList(): ArrayList<Note> {
        return (noteDao.getAllNote().map {
            Note(
                it.id, it.title, it.message, it.date
            )
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    suspend fun add(note: Note) {
        noteDao.addNote(
            NoteEntity(
                0,
                note.title,
                note.message,
                note.date
            )
        )
    }

//    fun deleteNote(note: Note) {
//        noteDao.deleteNote(NoteEntity(note.id, note.title, note.message, note.date))
//    }
    suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(NoteEntity(id, "", "", Date()))
    }
}