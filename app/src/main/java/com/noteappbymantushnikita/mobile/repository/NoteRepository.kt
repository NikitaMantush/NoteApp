package com.noteappbymantushnikita.mobile.repository

import com.noteappbymantushnikita.mobile.db.DataBase
import com.noteappbymantushnikita.mobile.db.NoteEntity
import com.noteappbymantushnikita.mobile.model.Note

class NoteRepository {

    fun getNoteList(): ArrayList<Note> {
        return (DataBase.noteDao?.getAllNote()?.map {
            Note(
                it.id, it.title, it.message, it.date
            )
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    fun add(note: Note) {
        DataBase.noteDao?.addNote(
            NoteEntity(
                0,
                note.title,
                note.message,
                note.date
            )
        )
    }

    fun deleteNote(note: Note) {
        DataBase.noteDao?.deleteNote(NoteEntity(note.id, note.title, note.message, note.date))
    }
}