package com.daniel.cabifyapp.base

import android.util.Log
import com.daniel.log.DebugTree

class MvpViewExceptionImpl : MvpViewException, RuntimeException() {

    override fun onAttachedException(): RuntimeException {
        val error = "Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter"
        val exception = RuntimeException(error)
        DebugTree().log(Log.ERROR, exception.localizedMessage)
        return exception
    }
}