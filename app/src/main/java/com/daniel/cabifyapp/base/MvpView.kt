package com.daniel.cabifyapp.base

interface MvpView : BaseView {

    fun onStart()

    fun onResume()

    fun onStop()

    fun onDestroy()

    fun onNextApplicationState()
}