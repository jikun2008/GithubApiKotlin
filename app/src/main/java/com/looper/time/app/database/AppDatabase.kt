package com.looper.time.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.looper.time.app.data.GitHubResponse

@Database(entities = arrayOf(GitHubResponse::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGitHubResponseDao(): GitHubResponseDao
}
    