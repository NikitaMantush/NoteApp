package com.noteappbymantushnikita.mobile.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noteappbymantushnikita.mobile.util.date.DateTypeConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(value = [DateTypeConverter::class])
abstract class AppDataBase: RoomDatabase() {
    abstract fun getNotedDAO(): NoteDao
}