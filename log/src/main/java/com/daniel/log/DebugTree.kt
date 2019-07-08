package com.daniel.log

import timber.log.Timber

class DebugTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        Timber.log(priority,throwable,message,throwable?.localizedMessage)
    }

    fun log(priority: Int,message: String){
        Timber.log(priority,message)

    }
}