package com.daniel.cabifyapp.application

import android.app.Application
import com.app.daniel.domain.dependency.DomainDependency
import com.daniel.analytics.AppAnalyser
import com.daniel.analytics.EventBuilder
import com.daniel.data.dependency.DataDependency
import com.daniel.log.AppLogger

class CabifyApp : Application() {

    override fun onCreate() {
        appInstance = this
        super.onCreate()
        AppLogger(this)
        AppAnalyser(this)
        EventBuilder().createEvent().addProperty(EventBuilder.STARTED_APP, System.currentTimeMillis().toString())
        val repository = DataDependency.SHARED.getRepository()
        DomainDependency.SHARED.inject(repository)
    }

    companion object {
        lateinit var appInstance: CabifyApp
    }
}