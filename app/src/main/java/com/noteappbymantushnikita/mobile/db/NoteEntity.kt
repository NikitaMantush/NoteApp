package com.noteappbymantushnikita.mobile.db

data class NoteEntity(
    val id: Int,
    val title: String,
    val message: String,
    val date: String?,
)