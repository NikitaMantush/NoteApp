package com.noteappbymantushnikita.mobile.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository

class NoteListViewModel: ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()

    private val repository = NoteRepository()
    fun loadListNote() {
        listNote.value = repository.getNoteList()
    }
    fun deleteNote(note: Note){
        repository.deleteNote(note)
        loadListNote()
    }
}