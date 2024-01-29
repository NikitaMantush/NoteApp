package com.noteappbymantushnikita.mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.databinding.FragmentLoginBinding

class LogInFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.returnSignupTitle?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, SignUpFragment())
                .addToBackStack(SignUpFragment.TAG)
                .commit()
        }
        binding?.loginEmailEdit?.doAfterTextChanged {
            validateLoginInput()
        }
        binding?.loginPasswordEdit?.doAfterTextChanged {
            validateLoginInput()
        }
        binding?.loginButton?.setOnClickListener {
            if (validateLoginInput()) {
                Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                    .show()
                parentFragmentManager.beginTransaction().replace(R.id.container, NoteListFragment())
                    .addToBackStack(SignUpFragment.TAG)
                    .commit()
            } else {
                Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val isEmailValid =
            validateNote(
                requireContext(),
                binding?.loginEmailInput,
                binding?.loginEmailEdit?.text.toString()
            )
        val isPasswordValid =
            validateNote(
                requireContext(),
                binding?.loginPasswordInput,
                binding?.loginPasswordEdit?.text.toString()
            )
        return isEmailValid && isPasswordValid
    }

    companion object {
        const val TAG = "LogInFragment"
    }
}