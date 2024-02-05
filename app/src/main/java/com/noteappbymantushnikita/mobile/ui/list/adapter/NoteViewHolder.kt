package com.noteappbymantushnikita.mobile.ui.list.adapter

import android.content.Context
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.ItemNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.util.date.getSimpleDate
import com.noteappbymantushnikita.mobile.util.truncateToDay
import java.util.Calendar

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    private val context: Context,
    private val onDeleteNoteSelected: (note: Note) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        note: Note,
        onClick: (note: String) -> Unit
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
        binding.dateTextView.text = note.date.getSimpleDate()

        binding.optionsButton.setOnClickListener {
            showOptionsDialog(note)
        }
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
            noteDate < todayDate -> R.color.error
            noteDate == todayDate -> R.color.green
            else -> R.color.list_item_color
        }

        binding.root.setBackgroundColor(ContextCompat.getColor(context, colorResId))
    }

    private fun showOptionsDialog(note: Note) {
        val options = arrayOf(
            context.getString(R.string.view),
            context.getString(R.string.edit),
            context.getString(R.string.delete)
        )
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.options))
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                    }

                    1 -> {
                    }

                    2 -> {
                        onDeleteNoteSelected(note)
                    }
                }
            }
            .show()
    }
}
