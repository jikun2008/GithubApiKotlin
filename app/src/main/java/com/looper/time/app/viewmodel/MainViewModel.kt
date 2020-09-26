package com.looper.time.app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.looper.time.app.api.retrofit.MainApi
import com.looper.time.app.base.BaseViewModel
import com.looper.time.app.data.GitHubResponse
import com.looper.time.app.database.AppDatabase
import com.looper.time.app.database.DbUtils
import com.yisingle.simulation.trip.driver.api.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MainViewModel(app: Application) : BaseViewModel(app) {

    var errorInfo: MutableLiveData<String> = MutableLiveData()

    var gitHubResponse: MutableLiveData<GitHubResponse> = MutableLiveData()

    var service = RetrofitClient.instance.getService(MainApi::class.java)

    var count: MutableLiveData<Int> = MutableLiveData(0)


    fun startLooperHttp() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //这里用了死循环 是因为  viewModelScope
                // 是ViewModle内部已经实现生命周期   onDestory的时候会 取消协程的
                while (true) {
                    count.postValue(count.value?.plus(1))
                    try {
                        var response = service.getHaveService()
                        gitHubResponse.postValue(response)
                        errorInfo.postValue("请求成功")
                        DbUtils.db.getGitHubResponseDao().insertGitHubResponse(response)
                    } catch (e: Exception) {
                        errorInfo.postValue("失败${e.toString()}")
                    } finally {
                        delay(5000)
                    }
                }
            }

        }

    }


}
