package com.daniel.cabifyapp.base

import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import com.daniel.cabifyapp.application.CabifyApp

class BasePresenter {

    private val context = CabifyApp.appInstance

    private fun buildWorkRequest(worker: Class<out ListenableWorker>, tag: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(worker)
            .addTag(tag)
            .build()
    }
}