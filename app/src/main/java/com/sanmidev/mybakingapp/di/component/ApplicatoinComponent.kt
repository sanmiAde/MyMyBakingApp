package com.sanmidev.mybakingapp.di.component

import android.app.Application
import com.sanmidev.mybakingapp.di.module.ApplicationModule
import com.sanmidev.mybakingapp.di.module.AssistedInjectModule
import com.sanmidev.mybakingapp.di.module.NetworkModule
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@ApplicationScope
@Component(modules = [NetworkModule::class, AssistedInjectModule::class, ApplicationModule::class])
interface ApplicatoinComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicatoinComponent
    }
}