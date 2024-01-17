package com.noteappbymantushnikita.mobile.noteAdapter

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.model.Note

class NoteViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val titleItem = view.findViewById<TextView>(R.id.title_textView)
    private val messageItem = view.findViewById<TextView>(R.id.message_textView)
    private val dateItem = view.findViewById<TextView>(R.id.date_textView)

    fun bind(note: Note, onClick:(note: String) -> Unit) {
        titleItem.run{
            text = note.title
            setOnClickListener{
                text = note.title
                onClick(note.title)
            }
        }
        messageItem.run {
            text = note.message
            setOnClickListener{
                toggleEllipsize()
            }
        }
        dateItem.text = note.date
    }
    private fun toggleEllipsize() {
        with(view.findViewById<TextView>(R.id.message_textView)) {
            if (maxLines == 2) {
                maxLines = Integer.MAX_VALUE
                ellipsize = null
            } else {
                maxLines = 2
                ellipsize = TextUtils.TruncateAt.END
            }
        }
    }
}