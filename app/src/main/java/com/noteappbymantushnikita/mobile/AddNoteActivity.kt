package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.model.NoteDB
import java.util.Date
import java.util.Locale

class AddNoteActivity : AppCompatActivity() {

    private var titleInputLayout: TextInputLayout? = null

    private var titleEditText: TextInputEditText? = null

    private var messageInputLayout: TextInputLayout? = null

    private var messageEditText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        titleEditText = findViewById(R.id.note_title)
        titleInputLayout = findViewById(R.id.note_title_layout)
        messageEditText = findViewById(R.id.note_message)
        messageInputLayout = findViewById(R.id.note_message_layout)
        val currentDate: Date = Calendar.getInstance().time
        val formatDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).
        format(currentDate)
        findViewById<TextView>(R.id.back_button_AddNoteActivity).setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }
        findViewById<TextView>(R.id.add_button).setOnClickListener {
            if (validateNoteInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                NoteDB.noteList.add(
                    Note(NoteDB.id,
                        titleEditText?.text.toString(),
                        messageEditText?.text.toString(),
                        formatDate
                    )
                )
                startActivity(Intent(this, NoteListActivity::class.java))
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
        titleEditText?.doAfterTextChanged {
            validateNote(this, titleInputLayout, titleEditText?.text.toString())
        }
        messageEditText?.doAfterTextChanged {
            validateNote(this, messageInputLayout, messageEditText?.text.toString())
        }
    }

    private fun validateNoteInput(): Boolean {
        val isValidTitle = validateNote(this, titleInputLayout, titleEditText?.text.toString())
        val isValidMessage = validateNote(this, messageInputLayout, messageEditText?.text.toString())
        return isValidTitle && isValidMessage
    }
}