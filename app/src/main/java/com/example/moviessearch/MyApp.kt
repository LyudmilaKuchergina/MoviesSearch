package com.example.moviessearch

import android.app.Application
import com.example.moviessearch.db.Db

class MyApp: Application() {
    override fun onCreate(){
        super.onCreate()
        Db.setupDb(applicationContext)
    }
}