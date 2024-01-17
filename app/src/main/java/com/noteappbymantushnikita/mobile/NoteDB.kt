package com.noteappbymantushnikita.mobile

object NoteDB {
    val noteList = mutableListOf<Note>()
    fun MutableList<Note>.getNoteList(): List<Note> {
        return this.toList()
    }
}