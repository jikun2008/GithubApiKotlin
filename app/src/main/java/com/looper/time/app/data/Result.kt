package com.looper.time.app.data

 data class Result<T>(var message: String?, var code: Int, var data: T?) {
     fun isSuccess() = code == 200
}