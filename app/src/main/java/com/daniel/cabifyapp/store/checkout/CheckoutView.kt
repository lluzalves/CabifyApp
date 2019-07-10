package com.daniel.cabifyapp.store.checkout

import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.base.MvpView

interface CheckoutView : MvpView {
    fun onOrderProcessing()
    fun onOrderCompleted(order: Order)
    fun onOrderProcessingError(localizedMessage: String)
}