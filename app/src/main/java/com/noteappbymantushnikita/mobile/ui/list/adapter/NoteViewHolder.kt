package com.noteappbymantushnikita.mobile.ui.list.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.R.*
import com.noteappbymantushnikita.mobile.databinding.ItemNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.util.date.getSimpleDate
import com.noteappbymantushnikita.mobile.util.truncateToDay
import java.util.Calendar

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        note: Note,
        onClick: (note: String) -> Unit,
        onClickId: (id: Int, view: View) -> Unit
    ) {
        binding.titleTextView.apply {
            text = note.title
            setOnClickListener {
                text = note.title
                onClick(note.title)
            }
        }
        binding.messageTextView.apply {
            text = note.message
            setOnClickListener {
                toggleEllipsize()
            }
        }
        binding.optionsButton.apply {
            setOnClickListener {
                onClickId(note.id, findViewById(R.id.options_button))
            }
        }
        binding.dateTextView.text = note.date.getSimpleDate()

        setItemBackgroundColor(context, note)
    }

    private fun toggleEllipsize() {
        with(binding.messageTextView) {
            if (maxLines == 2) {
                maxLines = Integer.MAX_VALUE
                ellipsize = null
            } else {
                maxLines = 2
                ellipsize = TextUtils.TruncateAt.END
            }
        }
    }

    private fun setItemBackgroundColor(context: Context, note: Note) {
        val todayDate = Calendar.getInstance().apply { truncateToDay() }.time
        val noteDate = Calendar.getInstance().apply { time = note.date; truncateToDay() }.time

        val colorResId = when {
            noteDate < todayDate -> color.error
            noteDate == todayDate -> color.green
            else -> color.list_item_color
        }

        binding.root.setBackgroundColor(ContextCompat.getColor(context, colorResId))
    }

}
