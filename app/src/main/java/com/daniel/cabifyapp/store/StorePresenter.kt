package com.daniel.cabifyapp.store

import com.daniel.cabifyapp.base.BasePresenter
import com.daniel.cabifyapp.dependency.ApplicationDependency

class StorePresenter : BasePresenter<StoreView>() {

    private val useCase = ApplicationDependency.SHARED.retrieveProductUseCase()

       fun requestStoreProducts() {
        useCase?.execute(
            onSuccess = { products ->
                mvpView?.getProducts(products)
            },
            onError = { throwable ->
                mvpView?.onError(throwable)
            },
            onFinished = {
                mvpView?.onCompleted()
            }
        )
    }


}