package com.noteappbymantushnikita.mobile

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.noteappbymantushnikita.mobile.databinding.ActivityAddNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.model.NoteDB
import java.util.Date
import java.util.Locale

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setDateButton.setOnClickListener {
            showDatePickerDialog()
        }
        binding.backButtonAddNoteActivity.setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }

        binding.addButton.setOnClickListener {
            if (validateNoteInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                NoteDB.noteList.add(
                    Note(
                        NoteDB.id,
                        binding.noteTitle.text.toString(),
                        binding.noteMessage.text.toString(),
                        binding.setDateButton.text.toString()
                    )
                )
                startActivity(Intent(this, NoteListActivity::class.java))
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }

        binding.noteTitle.doAfterTextChanged {
            validateNoteInput()
        }

        binding.noteMessage.doAfterTextChanged {
            validateNoteInput()
        }
        val currentDate: Date = Calendar.getInstance().time
        val formatDate: String =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentDate)
        binding.setDateButton.text = formatDate
    }

    private fun validateNoteInput(): Boolean {
        val isValidTitle =
            validateNote(this, binding.noteTitleLayout, binding.noteTitle.text.toString())
        val isValidMessage =
            validateNote(this, binding.noteMessageLayout, binding.noteMessage.text.toString())
        return isValidTitle && isValidMessage
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.setDateButton.text = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

}