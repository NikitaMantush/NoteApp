package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noteappbymantushnikita.mobile.model.NoteDB
import com.noteappbymantushnikita.mobile.noteAdapter.NoteListAdapter

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        findViewById<TextView>(R.id.logout_button).setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        findViewById<TextView>(R.id.add_note_button).setOnClickListener{
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteListAdapter {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter
        adapter.submitList(NoteDB.noteList)
    }
}