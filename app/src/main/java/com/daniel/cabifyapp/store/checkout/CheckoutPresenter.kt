package com.daniel.cabifyapp.store.checkout

import com.app.daniel.domain.entities.Order
import com.app.daniel.salesforce.commons.applyScheduler
import com.daniel.cabifyapp.base.BasePresenter
import com.daniel.cabifyapp.dependency.ApplicationDependency
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class CheckoutPresenter : BasePresenter<CheckoutView>() {

    private val startCheckoutUseCase = ApplicationDependency.SHARED.retrieveOrderUseCase()

    fun startCheckoutProcess(order: Order) {
        // since there is no backend to order processing we will do the following flow:
        // insert order into database
        // if successful, show order as completed
        startCheckoutUseCase?.buildInsertOrderUseCase(order)
            ?.doOnSubscribe { disposable -> disposables.add(disposable) }
            ?.applyScheduler()
            ?.subscribe(object : SingleObserver<Long> {
                override fun onSuccess(t: Long) {
                    if(t>-1) {
                        mvpView?.onOrderCompleted(order)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onError(e: Throwable) {
                    mvpView?.onOrderProcessingError(e.localizedMessage)
                }
            })

    }

    fun showOrderInfo(orderId: Long) {
        startCheckoutUseCase?.buildRetrieveOrderUseCase(orderId)
            ?.doOnSubscribe { disposable -> disposables.add(disposable) }
            ?.applyScheduler()
            ?.subscribeBy(
                onSuccess = { order ->
                    mvpView?.onOrderCompleted(order)
                },
                onError = {
                    mvpView?.onOrderProcessingError(it.localizedMessage)
                }
            )
    }
}