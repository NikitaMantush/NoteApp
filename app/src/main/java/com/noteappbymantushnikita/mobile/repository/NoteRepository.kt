package com.noteappbymantushnikita.mobile.repository

import com.noteappbymantushnikita.mobile.db.NoteDB
import com.noteappbymantushnikita.mobile.db.NoteEntity
import com.noteappbymantushnikita.mobile.model.Note

class NoteRepository {

    fun getNoteList(): ArrayList<Note>{
        return NoteDB.noteList.map{
            Note(
                it.id, it.title, it.message, it.date
            )
        }as ArrayList<Note>
    }
    fun add(note: Note) {
        NoteDB.noteList.add(
            NoteEntity(
                note.id,
                note.title,
                note.message,
                note.date
            )
        )
        id++
    }
    fun deleteNote(note: Note) {
        val noteEntityToDelete = NoteDB.noteList.find { it.id == note.id }
        if (noteEntityToDelete != null) {
            NoteDB.noteList.remove(noteEntityToDelete)
        }
    }
     companion object{
         var id: Int = 0
     }
}