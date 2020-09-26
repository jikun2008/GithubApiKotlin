package com.looper.time.app.error



class ResponseThrowable : Exception {
    var code: Int
    var errMsg: String



    constructor(code: Int, msg: String) {
        this.code = code
        this.errMsg = msg
    }

    override fun toString(): String {
        return "ResponseThrowable(code=$code, errMsg='$errMsg')"
    }


}
