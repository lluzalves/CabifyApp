package com.daniel.cabifyapp.application

import android.app.Application
import com.daniel.analytics.AppAnalyser
import com.daniel.analytics.EventBuilder
import com.daniel.cabifyapp.dependency.ApplicationDependency
import com.daniel.log.AppLogger

class CabifyApp : Application() {

    override fun onCreate() {
        appInstance = this
        super.onCreate()
        AppLogger(this)
        AppAnalyser(this)
        EventBuilder().createEvent().addProperty(EventBuilder.STARTED_APP, System.currentTimeMillis().toString())
    }

    companion object {
        lateinit var appInstance: CabifyApp
    }
}