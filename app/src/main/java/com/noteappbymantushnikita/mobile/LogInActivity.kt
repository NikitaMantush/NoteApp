package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_login)
        findViewById<TextView>(R.id.return_signup_title).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        findViewById<TextView>(R.id.login_button).setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }
        emailInputLayout = findViewById(R.id.login_email_input)
        emailEditText = findViewById(R.id.login_email_edit)
        passwordInputLayout = findViewById(R.id.login_password_input)
        passwordEditText = findViewById(R.id.login_password_edit)
        val logInButton: Button = findViewById(R.id.login_button)

        emailEditText?.doAfterTextChanged {
            validateNote(this, emailInputLayout, emailEditText?.text.toString())
        }
        passwordEditText?.doAfterTextChanged {
            validateNote(this, passwordInputLayout, passwordEditText?.text.toString())
        }

        logInButton.setOnClickListener {
            if (validateLoginInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, NoteListActivity::class.java))
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val isEmailValid = validateNote(this, emailInputLayout, emailEditText?.text.toString())
        val isPasswordValid =
            validateNote(this, passwordInputLayout, passwordEditText?.text.toString())
        return isEmailValid && isPasswordValid
    }

}