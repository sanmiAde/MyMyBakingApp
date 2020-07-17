package com.sanmidev.mybakingapp

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.sanmidev.mybakingapp.di.component.ApplicatoinComponent
import com.sanmidev.mybakingapp.di.component.DaggerApplicatoinComponent
import timber.log.Timber

class MyBakingApplication : Application() {

    val applicatoinComponent: ApplicatoinComponent by lazy {
        DaggerApplicatoinComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}