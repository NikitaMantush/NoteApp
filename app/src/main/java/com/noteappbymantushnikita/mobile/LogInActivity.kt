package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_login)
        findViewById<TextView>(R.id.return_signup_title).setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        val editEmail: TextInputEditText = findViewById(R.id.login_email_edit)
        val editEmailL: TextInputLayout = findViewById(R.id.login_email_input)
        val editPassword: TextInputEditText = findViewById(R.id.login_password_edit)
        val editPasswordL: TextInputLayout = findViewById(R.id.login_password_input)
        val logInButton: Button = findViewById(R.id.login_button)

        editEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validateEmail(editEmail, editEmailL)
            }
        })
        editPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                validatePassword(editPassword, editPasswordL)
            }
        })
        logInButton.setOnClickListener {
            val isEmailValid = validateEmail(editEmail, editEmailL)
            val isPasswordValid = validatePassword(editPassword, editPasswordL)
            if (isEmailValid && isPasswordValid)
            {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun validateEmail(editEmail: TextInputEditText, editEmailL: TextInputLayout): Boolean {
        return when {
            editEmail.text.toString().trim().isEmpty() -> {
                editEmailL.error = getString(R.string.field_empty)
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
        val password = editPassword.text.toString().trim()
        return when {
            password.isEmpty() -> {
                editPasswordL.error = getString(R.string.field_empty)
                false
            }
            else -> {
                editPasswordL.error = null
                true
            }
        }
    }
}