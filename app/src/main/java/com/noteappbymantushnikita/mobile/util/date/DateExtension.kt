package com.noteappbymantushnikita.mobile.util.date

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getSimpleDate(): String{
    val dateFormat = SimpleDateFormat(DateUtils.SIMPLE_DATE_PATTERN, Locale.getDefault())
    return dateFormat.format(this)
}
