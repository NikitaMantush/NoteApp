package com.noteappbymantushnikita.mobile.model

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val message: String,
    val date: String?,
    var isExpanded: Boolean = false,
    var isPastDue: Boolean = false
)
