package com.noteappbymantushnikita.mobile

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.noteappbymantushnikita.mobile.databinding.ActivityLoginBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        binding.returnSignupTitle.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.loginTitle.setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }
        binding.loginEmailEdit.doAfterTextChanged {
            validateLoginInput()
        }
        binding.loginPasswordEdit.doAfterTextChanged {
            validateLoginInput()
        }

        binding.loginButton.setOnClickListener {
            if (validateLoginInput()) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, NoteListActivity::class.java))
            } else {
                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val isEmailValid =
            validateNote(this, binding.loginEmailInput, binding.loginEmailEdit.text.toString())
        val isPasswordValid =
            validateNote(
                this,
                binding.loginPasswordInput,
                binding.loginPasswordEdit.text.toString()
            )
        return isEmailValid && isPasswordValid
    }

}