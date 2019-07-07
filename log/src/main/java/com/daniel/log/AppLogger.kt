package com.daniel.log
import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber



class AppLogger constructor(application: Application) {

    init {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(ReleaseTree())
        }
        AppCenter.start(application, application.getString(R.string.app_center_key), Crashes::class.java)
    }
}