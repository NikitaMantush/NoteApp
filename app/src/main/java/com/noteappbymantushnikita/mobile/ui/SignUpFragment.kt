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
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment
import com.noteappbymantushnikita.mobile.validateEmail
import com.noteappbymantushnikita.mobile.validateName
import com.noteappbymantushnikita.mobile.validatePassword

class SignUpFragment: Fragment() {
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
        binding?.signupLoginTitle?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, LogInFragment())
                .addToBackStack(LogInFragment.TAG)
                .commit()
        }
        binding?.signupFirstNameEdit?.doAfterTextChanged {
            validateSignupInput()
        }
        binding?.signupLastNameEdit?.doAfterTextChanged {
            validateSignupInput()
        }
        binding?.signupEmailEdit?.doAfterTextChanged {
            validateSignupInput()
        }
        binding?.signupPasswordEdit?.doAfterTextChanged {
            validateSignupInput()
        }
        binding?.signupButton?.setOnClickListener {
            if (validateSignupInput()) {
                Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG).show()
                parentFragmentManager.beginTransaction().replace(R.id.container, NoteListFragment())
                    .addToBackStack(NoteListFragment.TAG)
                    .commit()

            } else {
                Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun validateSignupInput(): Boolean {
        val isEmailValid = validateEmail(
            requireContext(),
            binding?.signupEmailInput,
            binding?.signupEmailEdit?.text.toString()
        )
        val isPasswordValid = validatePassword(
            requireContext(),
            binding?.signupPasswordInput,
            binding?.signupPasswordEdit?.text.toString()
        )
        val isFirstNameValid = validateName(
            requireContext(),
            binding?.signupFirstNameInput,
            binding?.signupFirstNameEdit?.text.toString()
        )
        val isLastNameValid = validateName(
            requireContext(),
            binding?.signupLastNameInput,
            binding?.signupLastNameEdit?.text.toString()
        )
        return isEmailValid && isPasswordValid && isFirstNameValid && isLastNameValid
    }
    companion object{

        const val TAG = "SignUpFragment"

    }
}