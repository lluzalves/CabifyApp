package com.daniel.analytics

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics

class AppAnalyser constructor(application: Application) {

    init {
        AppCenter.start(application, application.getString(R.string.app_center_key),Analytics::class.java)
    }

}