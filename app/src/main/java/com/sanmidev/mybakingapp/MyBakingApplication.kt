package com.sanmidev.mybakingapp

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class MyBakingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}