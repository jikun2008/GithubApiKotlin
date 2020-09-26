package com.looper.time.app.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.looper.time.app.R
import com.looper.time.app.base.BaseDialogFragment


class LoadingDialogFragment : BaseDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        return inflater.inflate(R.layout.loading_dialog, container, false)
    }


    companion object Builder {

        fun create(): LoadingDialogFragment {
            val fragment = LoadingDialogFragment()
            return fragment


        }


    }
}