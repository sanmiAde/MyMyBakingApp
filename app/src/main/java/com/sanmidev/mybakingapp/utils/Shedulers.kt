package com.sanmidev.mybakingapp.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


interface AppScheduler {
    fun IO(): Scheduler

    fun Main(): Scheduler
}

class RxSchedulers : AppScheduler {
    override fun IO(): Scheduler {
        return Schedulers.io()
    }

    override fun Main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}


class TestSchedulers : AppScheduler {
    override fun IO(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun Main(): Scheduler {
        return Schedulers.trampoline()
    }

}

fun <T> Observable<T>.applySchedulers(appScheduler: AppScheduler): Observable<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}

fun <T> Maybe<T>.applySchedulers(appScheduler: AppScheduler): Maybe<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}

fun <T> Single<T>.applySchedulers(appScheduler: AppScheduler): Single<T> {
    return subscribeOn(appScheduler.IO()).observeOn(appScheduler.Main())
}



