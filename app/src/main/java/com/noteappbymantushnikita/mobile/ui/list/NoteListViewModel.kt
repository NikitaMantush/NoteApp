package com.noteappbymantushnikita.mobile.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor (
    private val repository: NoteRepository
): ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()

    fun loadListNote() {
        listNote.value = repository.getNoteList()
    }
    fun deleteNote(noteId: Int){
        repository.deleteNote(noteId)
        loadListNote()
    }
}