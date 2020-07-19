package com.sanmidev.mybakingapp.di.module

import com.sanmidev.mybakingapp.data.remote.repo.BakingRepository
import com.sanmidev.mybakingapp.data.remote.repo.BakingRepositoryImp
import com.sanmidev.mybakingapp.di.scope.ApplicationScope
import com.sanmidev.mybakingapp.utils.AppScheduler
import com.sanmidev.mybakingapp.utils.RxSchedulers
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @ApplicationScope
    @Binds
    abstract fun bindsScheduler(rxSchedulers: RxSchedulers): AppScheduler

    @ApplicationScope
    @Binds
    abstract fun bindsBakingRepository(bakingRepositoryImp: BakingRepositoryImp): BakingRepository
}