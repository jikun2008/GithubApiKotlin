package com.looper.time.app.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.looper.time.app.base.BaseViewModel
import com.looper.time.app.data.GitHubResponse
import com.looper.time.app.database.DbUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HistoryOldViewModel(app: Application) : BaseViewModel(app) {

    var dataList: MutableLiveData<List<GitHubResponse>> = MutableLiveData()

    fun queryListBySql() {
        viewModelScope.launch(Dispatchers.IO) {
            DbUtils.db.getGitHubResponseDao().getGitHubResponses()
                .flowOn(Dispatchers.IO)
                .collect {
                    dataList.postValue(it)
                }
        }

    }

}