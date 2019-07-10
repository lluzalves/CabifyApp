package com.daniel.cabifyapp.store.checkout

import com.app.daniel.domain.entities.Order
import com.app.daniel.salesforce.commons.applyScheduler
import com.daniel.cabifyapp.base.BasePresenter
import com.daniel.cabifyapp.dependency.ApplicationDependency
import io.reactivex.rxkotlin.subscribeBy

class CheckoutPresenter : BasePresenter<CheckoutView>() {

    private val startCheckoutUseCase = ApplicationDependency.SHARED.retrieveOrderUseCase()

    fun startCheckoutProcess(order: Order) {
        // since there is no backend to order processing we will do the following flow:
        // insert order into database
        // if successful, show order as completed
        startCheckoutUseCase?.buildInsertOrderUseCase(order)
            ?.applyScheduler()
            ?.doOnSubscribe { disposable -> disposables.add(disposable) }
            ?.subscribeBy { it ->
                if(it.toInt() == -1){
                    mvpView?.onOrderProcessingError()
                }else{
                    mvpView?.onOrderCompleted()
                }
            }
    }
}