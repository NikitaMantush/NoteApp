package com.noteappbymantushnikita.mobile.util.date

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.TextView
import java.util.Date
import java.util.Locale

object DateUtils {

    const val SIMPLE_DATE_PATTERN = "dd/MM/yyyy"

    fun showDatePickerDialog(context: Context, textView: TextView, calendar: Calendar) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, monthOfYear)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                updateSelectedDate(textView, selectedDate)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun updateSelectedDate(textView: TextView, selectedDate: Calendar) {
        val dateFormat = SimpleDateFormat(SIMPLE_DATE_PATTERN, Locale.getDefault())
        textView.text = dateFormat.format(selectedDate.time)
    }
    fun setCurrentDate(): String {
        val currentDate: Date = Calendar.getInstance().time
        return SimpleDateFormat(SIMPLE_DATE_PATTERN, Locale.getDefault()).format(currentDate)
    }
}