package com.daniel.cabifyapp.base

import androidx.work.*
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.application.CabifyApp
import com.daniel.cabifyapp.store.StoreCart
import com.daniel.cabifyapp.work.NetworkStateIWorkState
import com.daniel.data.network.work.INetworkWorkState
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


abstract  class BasePresenter<V : MvpView> : MvpPresenter<V>, INetworkWorkState {
    private val context = CabifyApp.appInstance

    val disposables: CompositeDisposable = CompositeDisposable()
    var mvpView: V? = null
    lateinit var storeCart : StoreCart

    override fun attachView(mvpView: V) {
        this.mvpView = mvpView
    }

    fun currentCart(storeCart: StoreCart){
        this.storeCart = storeCart
    }

    override fun onDetach() {
        disposables.clear()
        mvpView = null
    }

    override fun checkConnectivity() {
        WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(
                NetworkStateIWorkState.NAME,
                ExistingWorkPolicy.REPLACE,
                buildWorkRequest(NetworkStateIWorkState::class.java, NetworkStateIWorkState.TAG)
            )
            .enqueue()
        WorkManager.getInstance(context.applicationContext).getWorkInfosByTagLiveData(NetworkStateIWorkState.TAG)
            .observeForever(this)
    }

    fun isViewAttached() {
        if (mvpView != null) return
        throw MvpViewExceptionImpl().onAttachedException()
    }

    override fun onConnectivity(isConnected: Boolean): Boolean {
        return isConnected
    }

    override fun onChanged(worksInfo: List<WorkInfo>) {
        worksInfo.indices.asSequence().map { workItem -> worksInfo[workItem] }.forEach { workItem ->
            when (workItem.state) {
                WorkInfo.State.ENQUEUED, WorkInfo.State.BLOCKED -> {
                    Timber.d("Network work is enqueued or blocked")
                }
                WorkInfo.State.RUNNING -> {
                    Timber.d("Network work is running")
                    mvpView?.onLoading(context.getString(R.string.please_wait))

                }
                WorkInfo.State.SUCCEEDED -> {
                    Timber.d("Network is available")
                    onConnectivity(isConnected = true)
                }
                WorkInfo.State.CANCELLED -> {
                    Timber.d("Network work was cancelled")
                    onConnectivity(isConnected = false)
                }
                WorkInfo.State.FAILED -> {
                    Timber.d("Network work failed or no network is available")
                    mvpView?.onError(context.getString(R.string.unable_to_detect_connectivity))
                }
            }
        }
        WorkManager.getInstance(context).getWorkInfosByTagLiveData(NetworkStateIWorkState.TAG).removeObserver(this)
        WorkManager.getInstance(context).pruneWork()
    }


    private fun buildWorkRequest(worker: Class<out ListenableWorker>, tag: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(worker)
            .addTag(tag)
            .build()
    }
}