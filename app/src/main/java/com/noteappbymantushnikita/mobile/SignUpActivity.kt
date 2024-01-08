package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_signup)
        findViewById<TextView>(R.id.signup_login_title).setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        val editFirstName: TextInputEditText = findViewById(R.id.signup_first_name_edit)
        val editFirstNameL: TextInputLayout = findViewById(R.id.signup_first_name_input)
        val editLastName: TextInputEditText = findViewById(R.id.signup_last_name_edit)
        val editLastNameL: TextInputLayout = findViewById(R.id.signup_last_name_input)
        val signUpButton: Button = findViewById(R.id.signup_button)

        editFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateName(editFirstName, editFirstNameL)
            }
        })
        editLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateName(editLastName, editLastNameL)
            }
        })

        val editEmail: TextInputEditText = findViewById(R.id.signup_email_edit)
        val editEmailL: TextInputLayout = findViewById(R.id.signup_email_input)

        editEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateEmail(editEmail, editEmailL)
            }
        })
        val editPassword: TextInputEditText = findViewById(R.id.signup_password_edit)
        val editPasswordL: TextInputLayout = findViewById(R.id.signup_password_input)

        editPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validatePassword(editPassword, editPasswordL)
            }
        })
        signUpButton.setOnClickListener {
            val isEmailValid = validateEmail(editEmail, editEmailL)
            val isPasswordValid = validatePassword(editPassword, editPasswordL)
            val isFirstNameValid = validateName(editFirstName, editFirstNameL)
            val isLastNameValid = validateName(editLastName, editLastNameL)

            if (isEmailValid && isPasswordValid && isFirstNameValid && isLastNameValid) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun validateEmail(editEmail: TextInputEditText, editEmailL: TextInputLayout): Boolean {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        return when {
            editEmail.text.toString().trim().isEmpty() -> {
                editEmailL.error = getString(R.string.email_empty)
                false
            }

            !editEmail.text.toString().trim().matches(emailPattern) -> {
                editEmailL.error = getString(R.string.email_incorrect_format)
                false
            }

            else -> {
                editEmailL.error = null
                true
            }
        }
    }

    private fun validatePassword(
        editPassword: TextInputEditText,
        editPasswordL: TextInputLayout
    ): Boolean {
        val passwordPattern =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-])")
        val password = editPassword.text.toString().trim()
        return when {
            password.isEmpty() -> {
                editPasswordL.error = getString(R.string.password_empty)
                false
            }

            password.length < 6 || password.length > 50 -> {
                editPasswordL.error = getString(R.string.password_length)
                false
            }

            !passwordPattern.containsMatchIn(password) -> {
                editPasswordL.error = getString(R.string.password_special_symbol)
                false
            }

            else -> {
                editPasswordL.error = null
                true
            }
        }
    }

    private fun validateName(editName: TextInputEditText, editNameL: TextInputLayout): Boolean {
        val name = editName.text.toString().trim()
        return when {
            name.isEmpty() -> {
                editNameL.error = getString(R.string.field_empty)
                false
            }

            name.length < 3 || name.length > 255 -> {
                editNameL.error = getString(R.string.field_length)
                false
            }

            else -> {
                editNameL.error = null
                true
            }
        }
    }
}