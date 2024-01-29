package com.noteappbymantushnikita.mobile

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.databinding.FragmentAddNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.model.NoteDB
import java.util.Date
import java.util.Locale

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.setDateButton?.setOnClickListener {
            showDatePickerDialog()
        }
        binding?.backButtonAddNoteActivity?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, NoteListFragment())
                .addToBackStack(NoteListFragment.TAG)
                .commit()
        }

        binding?.addButton?.setOnClickListener {
            if (validateNoteInput()) {
                Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                    .show()
                NoteDB.noteList.add(
                    Note(
                        NoteDB.id,
                        binding?.noteTitle?.text.toString(),
                        binding?.noteMessage?.text.toString(),
                        binding?.setDateButton?.text.toString()
                    )
                )
                parentFragmentManager.beginTransaction().replace(R.id.container, NoteListFragment())
                    .addToBackStack(NoteListFragment.TAG)
                    .commit()
            } else {
                Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding?.noteTitle?.doAfterTextChanged {
            validateNoteInput()
        }

        binding?.noteMessage?.doAfterTextChanged {
            validateNoteInput()
        }
        val currentDate: Date = Calendar.getInstance().time
        val formatDate: String =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentDate)
        binding?.setDateButton?.text = formatDate
    }

    private fun validateNoteInput(): Boolean {
        val isValidTitle =
            validateNote(
                requireContext(),
                binding?.noteTitleLayout,
                binding?.noteTitle?.text.toString()
            )
        val isValidMessage =
            validateNote(
                requireContext(),
                binding?.noteMessageLayout,
                binding?.noteMessage?.text.toString()
            )
        return isValidTitle && isValidMessage
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding?.setDateButton?.text = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    companion object{
        const val TAG = "AddNoteFragment"
    }
}