package com.noteappbymantushnikita.mobile.ui.note

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
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.model.Note
import com.noteappbymantushnikita.mobile.ui.MainFragment
import com.noteappbymantushnikita.mobile.util.date.DateUtils
import com.noteappbymantushnikita.mobile.util.setValidation
import com.noteappbymantushnikita.mobile.util.toDate
import com.noteappbymantushnikita.mobile.util.validation.ValidationResult
import com.noteappbymantushnikita.mobile.util.validation.validateEmptyField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                DateUtils.showDatePickerDialog(requireContext(), setDateButton, calendar)
            }
            backButtonAddNoteActivity.setOnClickListener {
                requireActivity().supportFragmentManager.openFragment(MainFragment())
            }

            addButton.setOnClickListener {
                if (validate()) {
                    Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                        .show()
                    val newNote = Note(
                        0,
                        title = binding?.noteTitle?.text.toString(),
                        message = binding?.noteMessage?.text.toString(),
                        date = binding?.setDateButton?.text.toString().toDate()
                    )
                    viewModel.addNote(newNote)
                    requireActivity().supportFragmentManager.openFragment(MainFragment())
                } else {
                    Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                        .show()
                }
            }
            noteTitle.doAfterTextChanged {
                validate()
            }

            noteMessage.doAfterTextChanged {
                validate()
            }
            setDateButton.text = DateUtils.setCurrentDate()
        }
    }
    private fun validate(): Boolean {
        val inputs = binding?.run {
            listOf(
                noteTitleLayout to validateEmptyField(noteTitleLayout.editText?.text.toString()),
                noteMessageLayout to validateEmptyField(noteMessageLayout.editText?.text.toString())
            )
        }

        inputs?.forEach { (input, validation) ->
            input.setValidation(validation)
        }
        return inputs?.all { (_, validation) -> validation is ValidationResult.Valid } ?: false
    }

    companion object {
        const val TAG = "AddNoteFragment"
    }
}