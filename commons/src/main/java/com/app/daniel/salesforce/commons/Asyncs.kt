package com.app.daniel.salesforce.commons

import com.app.daniel.salesforce.commons.AppSchedulers.mainThreadScheduler
import com.app.daniel.salesforce.commons.AppSchedulers.networkScheduler
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.async(): Observable<T> = this.compose { observable ->
    observable
        .subscribeOn(networkScheduler())
        .observeOn(mainThreadScheduler())
}

fun <T> Single<T>.async(): Single<T> = this.compose { single ->
    single
        .subscribeOn(networkScheduler())
        .observeOn(mainThreadScheduler())
}

object AppSchedulers{

    fun networkScheduler() : Scheduler {
        return Schedulers.io()
    }

    fun mainThreadScheduler() : Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun computationScheduler() : Scheduler {
        return Schedulers.computation()
    }

}