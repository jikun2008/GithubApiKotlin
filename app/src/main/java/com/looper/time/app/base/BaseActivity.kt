package com.yisingle.simulation.trip.driver.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.looper.time.app.base.BaseViewModel
import com.looper.time.app.dialog.LoadingDialogFragment
import kotlinx.coroutines.*


abstract class BaseActivity<VM : BaseViewModel, T : ViewDataBinding> : AppCompatActivity() {
    val uiScope = lifecycleScope
    lateinit var dataBinding: T
    lateinit var viewModel: VM
    private lateinit var loadingDialog: LoadingDialogFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        bindDataBinding()
        viewModel = initViewModelAndToDataBinding(dataBinding)
        initDialog()
        initView()

    }

    fun <T> launchResult(
        block: suspend CoroutineScope.() -> T,
        success: (T?) -> Unit,
        error: (Throwable) -> Unit,
        complete: () -> Unit = {}
    ) {
        uiScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    block()
                }
                success(result)
            } catch (e: Throwable) {
                error(e)
            } finally {
                complete()
            }
        }
    }


    fun initDialog() {
        loadingDialog = LoadingDialogFragment.create()
        viewModel.loadingStyle.observe(this@BaseActivity, Observer {
            it?.apply {
                if (it.isShow) {
                    showDialog()
                } else {
                    dissmissDialog()
                }
            }
        })
    }


    private fun bindDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.lifecycleOwner = this
    }


    abstract fun getLayoutId(): Int

    abstract fun initViewModelAndToDataBinding(dataBinding: T): VM

    abstract fun initView()


    private fun showDialog() {
        loadingDialog.show(supportFragmentManager, "")
    }

    private fun dissmissDialog() {
        loadingDialog.dismissAllowingStateLoss()

    }

}