package com.daniel.log

import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        Timber.log(priority,throwable,message,throwable?.localizedMessage)
    }
}