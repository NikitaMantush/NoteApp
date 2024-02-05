package com.noteappbymantushnikita.mobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository
import com.noteappbymantushnikita.mobile.ui.list.NoteListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_main)
        when {
            SharedPreferencesRepository.isFirstLaunch() -> {
                supportFragmentManager.openFragment(StartFragment())
                SharedPreferencesRepository.setIsFirstLaunch()
            }

            SharedPreferencesRepository.getUserEmail() == null -> {
                supportFragmentManager.openFragment(LogInFragment())
            }

            else -> {
                supportFragmentManager.openFragment(NoteListFragment())
            }
        }
    }
}