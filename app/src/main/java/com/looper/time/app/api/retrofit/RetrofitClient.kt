package com.yisingle.simulation.trip.driver.api.retrofit

import androidx.databinding.library.BuildConfig
import com.looper.time.app.api.retrofit.HeaderInterceptor
import com.looper.time.app.api.retrofit.LoggerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 */
class RetrofitClient
private constructor() {
    private val client: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        val logging =
            LoggerInterceptor(BuildConfig.DEBUG)
        builder.addInterceptor(logging)
            //这里可以添加请求头
            .addInterceptor(HeaderInterceptor())
            //设置连接5s超时
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        client = builder.build()
    }

    companion object {
        private const val TIME_OUT = 2

        val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }


    fun <S> getService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build().create(serviceClass)
    }

}