package com.noteappbymantushnikita.mobile.ui.list.adapter

import android.content.Context
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.ItemNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        binding.dateTextView.text = note.date

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

    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }

    private fun setItemBackgroundColor(context: Context, note: Note) {
        val colorResId = when {
            (note.date?.compareTo(getTodayDate()) ?: 1) < 0 -> R.color.error
            note.date == getTodayDate() -> R.color.green
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
