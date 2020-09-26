package com.looper.time.app.api.retrofit

import com.blankj.utilcode.util.AppUtils
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * okhttp全局header拦截器
 */
class HeaderInterceptor : Interceptor {
    private val versionName: String = AppUtils.getAppVersionName()
    private val versionCode: Int = AppUtils.getAppVersionCode()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {


//        val token= UserStateManager.getUser()?.token.toString()
//
        val request = chain.request()
            .newBuilder()
            .addHeader("versionName", versionName)
            .addHeader("versionCode", versionCode.toString())
            .build()

        return chain.proceed(request)
    }
}