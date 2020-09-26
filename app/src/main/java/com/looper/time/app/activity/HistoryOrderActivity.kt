package com.looper.time.app.activity

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.looper.time.app.R
import com.looper.time.app.adapter.DataBindingAdapter
import com.looper.time.app.base.BaseViewModel
import com.looper.time.app.data.GitHubResponse
import com.looper.time.app.database.DbUtils
import com.looper.time.app.databinding.ActivityHistoryOldBinding
import com.looper.time.app.viewmodel.HistoryOldViewModel
import com.yisingle.simulation.trip.driver.base.BaseActivity
import kotlinx.android.synthetic.main.activity_history_old.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryOrderActivity : BaseActivity<HistoryOldViewModel, ActivityHistoryOldBinding>() {


    private val adapter: DataBindingAdapter = DataBindingAdapter()
    override fun getLayoutId(): Int = R.layout.activity_history_old
    override fun initViewModelAndToDataBinding(dataBinding: ActivityHistoryOldBinding): HistoryOldViewModel {
        return ViewModelProvider(this).get(HistoryOldViewModel::class.java)
            .also { dataBinding.vm = it }
    }

    override fun initView() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.queryListBySql()
        }
        initRecyclerView()

        viewModel.dataList.observe(this, Observer {
            it?.also {
                if (it.isNotEmpty()) {
                    it[0].isChoose = true
                }
                adapter.setList(it)
            }
            swipeRefreshLayout.isRefreshing = false
        })
        viewModel.queryListBySql()


    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    fun testAdd(view: View) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            DbUtils.db.getGitHubResponseDao()
                .insertGitHubResponse(GitHubResponse(starredUrl = "java"))
        }

    }

}