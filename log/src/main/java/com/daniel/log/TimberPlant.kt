package com.daniel.log
import android.app.Application
import com.microsoft.appcenter.AppCenter
import timber.log.Timber
import com.microsoft.appcenter.crashes.Crashes



class TimberPlant constructor(application: Application) {

    init {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(ReleaseTree())
        }
        AppCenter.start(application, application.getString(R.string.app_center_key),Crashes::class.java)
    }
}