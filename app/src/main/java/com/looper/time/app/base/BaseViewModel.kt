package com.looper.time.app.base

import  com.looper.time.app.data.Result
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.looper.time.app.error.ExceptionHandle
import com.looper.time.app.error.ResponseThrowable

import kotlinx.coroutines.*


/**
 *
 *   time   : 2019/11/01
 */
open class BaseViewModel(app: Application) : AndroidViewModel(app), LifecycleObserver {
    class LoadingStyleData(var message: String, var isShow: Boolean)

    var loadingData = LoadingStyleData("加载中", false)
    var loadingStyle = MutableLiveData<LoadingStyleData>()

    fun getContext(): Context {
        return getApplication<Application>().applicationContext
    }




    fun <T> launchData(
        block: suspend CoroutineScope.() -> T,
        error: (e: Exception) -> Unit = {},
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        viewModelScope.launch {
            try {
                if (isShowDialog) showDialog()
                withContext(Dispatchers.IO) {
                    block()
                }
            } catch (e: Exception) {
                error(e)
            } finally {
                dissmissDialog()
                complete()
            }
        }
    }


    fun showToast(msg: String) {
        ToastUtils.showShort(msg)
    }

    fun showDialog() {

        loadingData.isShow = true
        loadingStyle.postValue(loadingData)
    }

    fun dissmissDialog() {
        loadingData.isShow = false
        loadingStyle.postValue(loadingData)
    }
}