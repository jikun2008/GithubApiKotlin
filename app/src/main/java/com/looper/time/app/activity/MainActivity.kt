package com.looper.time.app.activity

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.Stetho
import com.looper.time.app.R
import com.looper.time.app.databinding.ActivityMainBinding
import com.looper.time.app.viewmodel.MainViewModel
import com.yisingle.simulation.trip.driver.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initViewModelAndToDataBinding(dataBinding: ActivityMainBinding): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
            .also { dataBinding.vm = it }
    }

    override fun initView() {
        viewModel.startLooperHttp()
    }

    fun toHistory(view: View) {
        var intent = Intent(this, HistoryOrderActivity::class.java)
        startActivity(intent)
    }

}