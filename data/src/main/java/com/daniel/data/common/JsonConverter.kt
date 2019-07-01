package com.daniel.data.common

import com.google.gson.Gson

interface JsonConverter {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

inline fun <reified T : JsonConverter> String.toObject(): T = Gson().fromJson(this, T::class.java)