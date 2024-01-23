package com.noteappbymantushnikita.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.noteappbymantushnikita.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        binding.maimLoginTitle.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        binding.discoverPlatformButton.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}