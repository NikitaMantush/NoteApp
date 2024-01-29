package com.noteappbymantushnikita.mobile.ui.note

import androidx.lifecycle.ViewModel
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository

class AddNoteViewModel: ViewModel() {

    private val repository = NoteRepository()
    fun addNote(note: Note) = repository.add(note)

}
