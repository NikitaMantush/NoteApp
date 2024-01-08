package com.noteappbymantushnikita.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.maim_login_title).setOnClickListener{
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.discover_platform_button).setOnClickListener{
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }
    }
}