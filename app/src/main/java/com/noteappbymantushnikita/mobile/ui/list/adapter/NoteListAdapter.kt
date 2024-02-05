package com.noteappbymantushnikita.mobile.ui.list.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.noteappbymantushnikita.mobile.databinding.ItemNoteBinding
import com.noteappbymantushnikita.mobile.model.Note


class NoteListAdapter(
    private val onClick: (note: String) -> Unit,
    private val onDeleteNoteSelected: (note: Note) -> Unit
) : ListAdapter<Note, NoteViewHolder>(object : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val context = parent.context
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, context, onDeleteNoteSelected)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}