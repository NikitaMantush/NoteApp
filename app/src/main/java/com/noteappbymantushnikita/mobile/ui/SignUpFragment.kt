package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentSignupBinding
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment
import com.noteappbymantushnikita.mobile.util.validation.ValidationResult
import com.noteappbymantushnikita.mobile.util.setValidation
import com.noteappbymantushnikita.mobile.util.validation.validateEmail
import com.noteappbymantushnikita.mobile.util.validation.validateName
import com.noteappbymantushnikita.mobile.util.validation.validatePassword

class SignUpFragment : Fragment() {
    private var binding: FragmentSignupBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            signupLoginTitle.setOnClickListener {
                parentFragmentManager.openFragment(LogInFragment(), LogInFragment.TAG)
            }
            signupFirstNameEdit.doAfterTextChanged {
                validate()
            }
            signupLastNameEdit.doAfterTextChanged {
                validate()
            }
            signupEmailEdit.doAfterTextChanged {
                validate()
            }
            signupPasswordEdit.doAfterTextChanged {
                validate()
            }
            signupButton.setOnClickListener {
                if (validate()) {
                    Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                        .show()
                    parentFragmentManager.openFragment(NoteListFragment(), NoteListFragment.TAG)
                } else {
                    Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }

    private fun validate(): Boolean {
        val inputs = binding?.run {
            listOf(
                signupEmailInput to validateEmail(signupEmailInput.editText?.text.toString()),
                signupPasswordInput to validatePassword(signupPasswordInput.editText?.text.toString()),
                signupFirstNameInput to validateName(signupFirstNameInput.editText?.text.toString()),
                signupLastNameInput to validateName(signupLastNameInput.editText?.text.toString())
            )
        }

        inputs?.forEach { (input, validation) ->
            input.setValidation(validation)
        }

        return inputs?.all { (_, validation) -> validation is ValidationResult.Valid } ?: false
    }



    companion object {

        const val TAG = "SignUpFragment"

    }
}