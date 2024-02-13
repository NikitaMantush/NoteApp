package com.noteappbymantushnikita.mobile.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor (
    private val repository: NoteRepository
): ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()

    private var job: Job? = null

    fun loadListNote() {
        job?.cancelChildren()
        job = viewModelScope.launch(Dispatchers.IO) {
            listNote.postValue(repository.getNoteList())
        }
    }
    fun deleteNote(noteId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(noteId)
            loadListNote()
        }
    }
}