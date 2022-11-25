package com.example.moviessearch.db

import android.content.Context
import androidx.room.Room

object Db {
    private var INSTANCE: AppDb? = null

    fun setupDb(context: Context) {
        if (INSTANCE == null){
            synchronized(AppDb::class){
                INSTANCE = Room.databaseBuilder(context, AppDb::class.java, "db-name.db")
                    .build()
            }
        }
    }

    fun getInstance() : AppDb = INSTANCE!!

}