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
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    private var firstNameInputLayout: TextInputLayout? = null
    private var firstNameEditText: TextInputEditText? = null

    private var lastNameInputLayout: TextInputLayout? = null
    private var lastNameEditText: EditText? = null

    private var emailInputLayout: TextInputLayout? = null
    private var emailEditText: TextInputEditText? = null

    private var passwordInputLayout: TextInputLayout? = null
    private var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_signup)
        findViewById<TextView>(R.id.signup_login_title).setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        lastNameInputLayout = findViewById(R.id.signup_last_name_input)
        lastNameEditText = findViewById(R.id.signup_last_name_edit)
        firstNameInputLayout = findViewById(R.id.signup_first_name_input)
        firstNameEditText = findViewById(R.id.signup_first_name_edit)
        emailInputLayout = findViewById(R.id.signup_email_input)
        emailEditText = findViewById(R.id.signup_email_edit)
        passwordInputLayout = findViewById(R.id.signup_password_input)
        passwordEditText = findViewById(R.id.signup_password_edit)
        val signUpButton: Button = findViewById(R.id.signup_button)

        firstNameEditText?.doAfterTextChanged {
            validateName(this, firstNameInputLayout, firstNameEditText?.text.toString())
        }
        lastNameEditText?.doAfterTextChanged {
            validateName(this, lastNameInputLayout, lastNameEditText?.text.toString())
        }
        emailEditText?.doAfterTextChanged {
            validateEmail(this, emailInputLayout, emailEditText?.text.toString())
        }
        passwordEditText?.doAfterTextChanged {
            validatePassword(this, passwordInputLayout, passwordEditText?.text.toString())
        }

        signUpButton.setOnClickListener {
            if (validateSignupInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, NoteListActivity::class.java))

            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateSignupInput(): Boolean {
        val isEmailValid = validateEmail(this, emailInputLayout, emailEditText?.text.toString())
        val isPasswordValid = validatePassword(this, passwordInputLayout, passwordEditText?.text.toString())
        val isFirstNameValid = validateName(this, firstNameInputLayout, firstNameEditText?.text.toString())
        val isLastNameValid = validateName(this, lastNameInputLayout, lastNameEditText?.text.toString())

        return isEmailValid && isPasswordValid && isFirstNameValid && isLastNameValid
    }
}