package com.looper.time.app.database

import androidx.room.*
import com.looper.time.app.data.GitHubResponse
import kotlinx.coroutines.flow.Flow

/**
 * 使用协程
 */
@Dao
interface GitHubResponseDao {
    //这里 Flow类似RxJava的发射流
    @Query("SELECT * FROM gitHubResponse order by uid desc")
    fun getGitHubResponsesByFlow(): Flow<List<GitHubResponse>>

    @Query("SELECT * FROM gitHubResponse order by uid desc")
    suspend fun getGitHubResponses(): List<GitHubResponse>

    @Insert
    suspend fun insertGitHubResponse(gitHubResponse: GitHubResponse)

    @Update
    suspend fun updateGitHubResponse(gitHubResponse: GitHubResponse)

    @Delete
    suspend fun deleteGitHubResponse(gitHubResponse: GitHubResponse)

}