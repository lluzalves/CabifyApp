package com.daniel.cabifyapp.application

import android.app.Application
import com.daniel.analytics.AppAnalyser
import com.daniel.analytics.EventBuilder
import com.daniel.log.TimberPlant

class CabifyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TimberPlant(this)
        AppAnalyser(this)
        EventBuilder().createEvent().addProperty(EventBuilder.STARTED_APP, System.currentTimeMillis().toString())
    }
}