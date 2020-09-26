package com.looper.time.app.database

import androidx.room.Room

import com.blankj.utilcode.util.Utils

class DbUtils {
    companion object {
        val db = Room.databaseBuilder(
            Utils.getApp().applicationContext,
            AppDatabase::class.java, "githubdb"
        ).build()
    }
}