package com.daniel.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.concurrent.futures.ResolvableFuture
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture

class NetworkStateWork constructor(private val appContext: Context, params: WorkerParameters) :
    ListenableWorker(appContext, params), Observer<List<WorkInfo>> {
    private var resolvableFuture: ResolvableFuture<Result> = ResolvableFuture.create()
    private lateinit var works: LiveData<List<WorkInfo>>

    override fun startWork(): ListenableFuture<Result> {
        works = WorkManager.getInstance(appContext).getWorkInfosByTagLiveData(TAG)
        works.observeForever(this)
        val connectivityManager =
            appContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        when {
            activeNetwork != null && activeNetwork.isConnected -> {
                resolvableFuture.set(Result.success())
                WorkManager.getInstance(appContext).pruneWork()
            }
            else -> {
                resolvableFuture.set(Result.failure())
                WorkManager.getInstance(appContext).pruneWork()
            }
        }
        return resolvableFuture
    }

    override fun onChanged(worksInfo: List<WorkInfo>) {
        worksInfo.indices.asSequence().map { workItem -> worksInfo[workItem] }.forEach { workItem ->
            when (workItem.state) {
                WorkInfo.State.ENQUEUED, WorkInfo.State.BLOCKED -> {
                    Log.d("Debug", "Network work is enqueued or blocked")
                }
                WorkInfo.State.RUNNING -> {
                    Log.d("Debug", "Network work is running")
                }
                WorkInfo.State.SUCCEEDED -> {
                    Log.d("Debug", "Network is available")
                }
                WorkInfo.State.CANCELLED -> {
                    Log.d("Debug", "Network work was cancelled")
                }
                WorkInfo.State.FAILED -> {
                    Log.d("Debug", "Network work failed or no network is available")
                }
            }
        }
        works.removeObserver(this)
    }

    companion object {
        const val TAG = "network_worker"
    }

}