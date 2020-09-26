package com.looper.time.app.api.retrofit

import android.util.Log

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

/**
 * 日志打印拦截器
 * Created by yu on 16/3/1.
 */
class LoggerInterceptor(private val printLog: Boolean) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logForRequest(request)
        val response = chain.proceed(request)
        return logForResponse(response)

    }

    private fun logForResponse(response: Response): Response {
        try {
            val builder = response.newBuilder()
            val clone = builder.build()

            val url = clone.request.url
            //            String tag = ">" + url.pathSegments().get(url.pathSize() - 1);

            Log.e("###", "╔════════════response start══════════════════════════════════════════")
            Log.e("###", "║ " + clone.code + "  " + clone.message + "  " + url)

            if (printLog) {
                var body = clone.body
                if (body != null) {
                    val mediaType = body.contentType()
                    if (mediaType != null) {
                        Log.e("###", "║ contentType: $mediaType")
                        if (isText(mediaType)) {
                            val resp = body.string()
                            Log.e("###", "║ content: $resp")

                            body = ResponseBody.create(mediaType, resp)
                            Log.e(
                                "###",
                                "╚════════════response end════════════════════════════════════════════"
                            )
                            return response.newBuilder().body(body).build()
                        } else {
                            Log.e(
                                "###",
                                "║ responseBody's content : maybe [file part] , too large too print , ignored!"
                            )
                        }
                    }
                }
            }

            Log.e("###", "╚════════════response end════════════════════════════════════════════")
        } catch (e: Exception) {
            //            e.printStackTrace();
        }

        return response
    }

    private fun logForRequest(request: Request) {
        try {
            val url = request.url
            //            String tag = ">" + url.pathSegments().get(url.pathSize() - 1);

            Log.e("###", "╔════════════request start══════════════════════════════════════════")
            Log.e("###", "║ " + request.method + ' '.toString() + url)

            if (printLog) {
                val headers = request.headers
                if (headers != null && headers.size > 0) {
                    for (i in 0 until headers.size) {
                        Log.e("##$i", "║ " + headers.name(i) + ": " + headers.value(i))
                    }
                    //                Log.e("###", "║ headers : " + headers.toString().replace("\n", " ║ "));
                }
                val requestBody = request.body
                if (requestBody != null) {
                    val mediaType = requestBody.contentType()
                    if (mediaType != null) {
                        Log.e("###", "║ contentType : $mediaType")
                        if (isText(mediaType)) {
                            Log.e("###", "║ content : " + bodyToString(request))
                        } else {
                            Log.e(
                                "###",
                                "║ content : maybe [file part] , too large too print , ignored!"
                            )
                        }
                    }
                }
            }
            Log.e("###", "╚════════════request end════════════════════════════════════════════")
        } catch (e: Exception) {
            //            e.printStackTrace();
        }

    }

    private fun isText(mediaType: MediaType): Boolean {
        if (mediaType.type != null && mediaType.type == "text") {
            return true
        }
        if (mediaType.subtype != null) {
            if (mediaType.subtype == "json" ||
                mediaType.subtype == "xml" ||
                mediaType.subtype == "html" ||
                mediaType.subtype == "webviewhtml"
            )
                return true
        }
        return false
    }

    private fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body!!.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "something error when show requestBody."
        }

    }
}
