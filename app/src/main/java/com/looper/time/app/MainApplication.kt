package com.looper.time.app

import android.app.Application
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        Utils.init(this)
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            Stetho.initializeWithDefaults(this)
        }
        ARouter.init(this)

    }
}