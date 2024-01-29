package com.noteappbymantushnikita.mobile.ui.note

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
import androidx.fragment.app.viewModels
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentAddNoteBinding
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.repository.NoteRepository
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment
import com.noteappbymantushnikita.mobile.validateNote
import java.util.Date
import java.util.Locale

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null

    private val calendar = Calendar.getInstance()

    private val viewModel: AddNoteViewModel by viewModels()


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

        binding?.run {

            setDateButton.setOnClickListener {
                showDatePickerDialog()
            }

            backButtonAddNoteActivity.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.container, NoteListFragment())
                    .addToBackStack(NoteListFragment.TAG)
                    .commit()
            }

            addButton.setOnClickListener {
                if (validateNoteInput()) {
                    Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                        .show()
                    val newNote = Note(
                        id = NoteRepository.id,
                        title = binding?.noteTitle?.text.toString(),
                        message = binding?.noteMessage?.text.toString(),
                        date = binding?.setDateButton?.text.toString()
                    )
                    viewModel.addNote(newNote)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, NoteListFragment())
                        .addToBackStack(NoteListFragment.TAG)
                        .commit()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                        .show()
                }
            }

            noteTitle.doAfterTextChanged {
                validateNoteInput()
            }

            noteMessage.doAfterTextChanged {
                validateNoteInput()
            }
            setDateButton.text = setCurrentDate()
        }
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
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, monthOfYear)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                updateSelectedDate(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateSelectedDate(selectedDate: Calendar) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding?.setDateButton?.text = dateFormat.format(selectedDate.time)
    }

    private fun setCurrentDate(): String {
        val currentDate: Date = Calendar.getInstance().time
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentDate)
    }

    companion object {
        const val TAG = "AddNoteFragment"
    }
}