package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.noteappbymantushnikita.mobile.databinding.ActivityNoteListBinding
import com.noteappbymantushnikita.mobile.model.NoteDB
import com.noteappbymantushnikita.mobile.noteAdapter.NoteListAdapter

class NoteListActivity : AppCompatActivity() {

    private lateinit var adapter: NoteListAdapter
    private lateinit var binding: ActivityNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logoutButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        binding.addNoteButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
        val recyclerView = binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@NoteListActivity)
        }
        adapter = NoteListAdapter(
            onClick = { noteTitle ->
                Toast.makeText(this, noteTitle, Toast.LENGTH_LONG).show()
            },
            onDeleteNoteSelected = { note ->
                NoteDB.noteList.remove(note)
                adapter.notifyDataSetChanged()
            }
        )
        recyclerView.adapter = adapter
        adapter.submitList(NoteDB.noteList)
    }
}