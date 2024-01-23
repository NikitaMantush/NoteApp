package com.noteappbymantushnikita.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.noteappbymantushnikita.mobile.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        binding.signupLoginTitle.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        binding.signupFirstNameEdit.doAfterTextChanged {
            validateSignupInput()
        }
        binding.signupLastNameEdit.doAfterTextChanged {
            validateSignupInput()
        }
        binding.signupEmailEdit.doAfterTextChanged {
            validateSignupInput()
        }
        binding.signupPasswordEdit.doAfterTextChanged {
            validateSignupInput()
        }
        binding.signupButton.setOnClickListener {
            if (validateSignupInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, NoteListActivity::class.java))

            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateSignupInput(): Boolean {
        val isEmailValid = validateEmail(
            this,
            binding.signupEmailInput,
            binding.signupEmailEdit.text.toString()
        )
        val isPasswordValid = validatePassword(
            this,
            binding.signupPasswordInput,
            binding.signupPasswordEdit.text.toString()
        )
        val isFirstNameValid = validateName(
            this,
            binding.signupFirstNameInput,
            binding.signupFirstNameEdit.text.toString()
        )
        val isLastNameValid = validateName(
            this,
            binding.signupLastNameInput,
            binding.signupLastNameEdit.text.toString()
        )
        return isEmailValid && isPasswordValid && isFirstNameValid && isLastNameValid
    }
}