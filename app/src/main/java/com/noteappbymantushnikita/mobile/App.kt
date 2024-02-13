package com.noteappbymantushnikita.mobile

import android.app.Application
import com.noteappbymantushnikita.mobile.db.DataBase
import com.noteappbymantushnikita.mobile.repository.SharedPreferencesRepository

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesRepository.init(applicationContext)
        DataBase.init(applicationContext)
    }
}