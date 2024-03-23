package com.noteappbymantushnikita.mobile.util

import java.util.Calendar

fun Calendar.truncateToDay() {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}