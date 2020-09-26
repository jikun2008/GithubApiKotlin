package com.looper.time.app.database

import androidx.room.*
import com.looper.time.app.data.GitHubResponse
import kotlinx.coroutines.flow.Flow

/**
 * 使用协程
 */
@Dao
interface GitHubResponseDao {

    @Query("SELECT * FROM gitHubResponse order by uid desc")
    fun getGitHubResponses(): Flow<List<GitHubResponse>>


    @Insert
    suspend fun insertGitHubResponse(gitHubResponse: GitHubResponse)

    @Update
    suspend fun updateGitHubResponse(gitHubResponse: GitHubResponse)

    @Delete
    suspend fun deleteGitHubResponse(gitHubResponse: GitHubResponse)

}