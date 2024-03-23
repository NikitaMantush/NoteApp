package com.noteappbymantushnikita.mobile.util

import android.icu.text.SimpleDateFormat
import com.noteappbymantushnikita.mobile.util.date.DateUtils

import java.util.Date
import java.util.Locale

fun String.toDate(): Date {
    val dateFormat = SimpleDateFormat(DateUtils.SIMPLE_DATE_PATTERN, Locale.getDefault())
    return dateFormat.parse(this)
}

