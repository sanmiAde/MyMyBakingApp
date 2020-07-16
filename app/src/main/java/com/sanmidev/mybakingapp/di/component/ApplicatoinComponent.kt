package com.sanmidev.mybakingapp.di.component

import com.sanmidev.mybakingapp.di.module.AssistedInjectModule
import com.sanmidev.mybakingapp.di.module.NetworkModule
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import dagger.Component
import dagger.Module

@ApplicationScope
@Component(modules = [NetworkModule::class, AssistedInjectModule::class])
interface ApplicatoinComponent {
}