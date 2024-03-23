package com.noteappbymantushnikita.mobile.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "NoteEntity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("message") val message: String,
    @ColumnInfo("date") val date: Date
)