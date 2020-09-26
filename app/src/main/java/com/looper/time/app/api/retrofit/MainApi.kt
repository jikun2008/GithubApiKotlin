package com.looper.time.app.api.retrofit

import com.looper.time.app.data.GitHubResponse
import retrofit2.http.GET


@JvmSuppressWildcards
interface MainApi {

    @GET("https://api.github.com/")
    suspend fun getHaveService(): GitHubResponse

}