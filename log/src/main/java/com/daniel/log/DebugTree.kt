package com.daniel.log

import android.util.Log
import timber.log.Timber

class DebugTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        Timber.log(Log.DEBUG,throwable,message,throwable?.localizedMessage)
    }
}