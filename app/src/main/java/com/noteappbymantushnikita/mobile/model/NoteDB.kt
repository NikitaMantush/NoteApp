package com.noteappbymantushnikita.mobile.model

object NoteDB {
    val noteList = ArrayList<Note>()
    var id: Int = 0
    fun add(note: Note){
        noteList.add(note)
        id++
    }
    fun delete(note: Note){
        noteList.remove(note)
    }
}