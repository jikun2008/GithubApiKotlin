package com.looper.time.app.error

import androidx.databinding.library.BuildConfig
import com.blankj.utilcode.util.LogUtils

object ExceptionHandle {

    fun handleException(e: Throwable): ResponseThrowable {
        var ex= ResponseThrowable(-9999,"未知错误")

        if(e is ResponseThrowable){
           ex = e
        }else{
            if(BuildConfig.DEBUG){
                LogUtils.e(e.toString())
            }
            if (!e.message.isNullOrEmpty())
            {
                ex.errMsg= e.message!!
            }

        }

        return ex
    }
}