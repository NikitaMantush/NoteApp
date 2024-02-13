package com.noteappbymantushnikita.mobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.noteappbymantushnikita.mobile.R
import com.noteappbymantushnikita.mobile.util.openFragment
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_main)
        when {
            sharedPreferencesRepository.isFirstLaunch() -> {
                supportFragmentManager.openFragment(StartFragment())
                sharedPreferencesRepository.setIsFirstLaunch()
            }
            sharedPreferencesRepository.getUserEmail() == null -> {
                supportFragmentManager.openFragment(LogInFragment())
            }
            else -> {
                supportFragmentManager.openFragment(MainFragment())
            }
        }
    }
}