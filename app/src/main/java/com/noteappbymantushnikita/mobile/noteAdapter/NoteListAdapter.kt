package com.noteappbymantushnikita.mobile.noteAdapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.model.Note


class NoteListAdapter(private val onClick:(note: String) -> Unit): ListAdapter<Note, NoteViewHolder>(object : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position),onClick)
    }
}