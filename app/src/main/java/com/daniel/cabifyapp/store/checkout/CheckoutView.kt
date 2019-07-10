package com.daniel.cabifyapp.store.checkout

import com.daniel.cabifyapp.base.MvpView

interface CheckoutView : MvpView {
    fun onOrderProcessing()
    fun onOrderCompleted()
    fun onOrderProcessingError()
}