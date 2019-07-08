package com.daniel.cabifyapp.base

interface BaseView {

    fun onLoading(loadingMessage : String)

    fun onCompleted()

    fun onError(errorMessage : String)

    fun onError(throwable: Throwable)

    fun hideKeyboard()

}