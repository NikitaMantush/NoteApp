package com.noteappbymantushnikita.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.databinding.FragmentLoginBinding
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment
import com.noteappbymantushnikita.mobile.util.validation.ValidationResult
import com.noteappbymantushnikita.mobile.util.setValidation
import com.noteappbymantushnikita.mobile.util.validation.validateEmptyField

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
        binding?.run {
            returnSignupTitle.setOnClickListener {
                parentFragmentManager.openFragment(SignUpFragment(), SignUpFragment.TAG)
            }
            loginButton.setOnClickListener {
                if (validate()) {
                    Toast.makeText(requireContext(), getString(R.string.success), Toast.LENGTH_LONG)
                        .show()
                    val email = loginEmailEdit.text.toString()
                    SharedPreferencesRepository.setUserEmail(email)
                    parentFragmentManager.openFragment(NoteListFragment(), NoteListFragment.TAG)
                } else {
                    Toast.makeText(requireContext(), getString(R.string.failed), Toast.LENGTH_LONG)
                        .show()
                }
            }
            loginEmailEdit.doAfterTextChanged {
                validate()
            }
            loginPasswordEdit.doAfterTextChanged {
                validate()
            }
        }
    }

    private fun validate(): Boolean {
        val inputs = binding?.run {
            listOf(
                loginEmailInput to validateEmptyField(loginEmailInput.editText?.text.toString()),
                loginPasswordInput to validateEmptyField(loginPasswordInput.editText?.text.toString())
            )
        }
        inputs?.forEach { (input, validation) ->
            input.setValidation(validation)
        }
        return inputs?.all { (_, validation) -> validation is ValidationResult.Valid } ?: false
    }

    companion object {
        const val TAG = "LogInFragment"
    }
}