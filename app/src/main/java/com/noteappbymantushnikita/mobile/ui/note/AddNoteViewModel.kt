package com.noteappbymantushnikita.mobile.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {
    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(note)
        }
    }

}
