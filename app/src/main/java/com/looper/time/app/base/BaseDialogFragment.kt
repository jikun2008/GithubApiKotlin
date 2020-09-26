package com.looper.time.app.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager

import androidx.fragment.app.DialogFragment

/**
 * Created by jikun on 17/3/24.
 */

open class BaseDialogFragment : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    private fun init() {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)// 设置Dialog为无标题模式
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)// 隐藏软键盘
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)// 设置Dialog为无标题模式
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))// 设置Dialog背景色为透明
    }
}
