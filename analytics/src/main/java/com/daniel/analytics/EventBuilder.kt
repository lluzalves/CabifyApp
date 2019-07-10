package com.daniel.analytics

import com.microsoft.appcenter.analytics.Analytics
import java.util.HashMap

class EventBuilder {
    private lateinit var event : MutableMap<String, String>


    fun createEvent(): EventBuilder {
        event = HashMap()
        return this
    }

    fun addProperty(key:String, value:String):EventBuilder{
        event[key] = value
        return this
    }

    fun build(name: String){
        Analytics.trackEvent(name, event)
    }


    companion object{
        const val STARTED_APP = "started app"
        const val CHECKING_NETWORK = "network checker"
        const val PURCHASE = "purchase"
    }
}