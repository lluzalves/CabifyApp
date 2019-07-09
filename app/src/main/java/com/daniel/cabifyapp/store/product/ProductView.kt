package com.daniel.cabifyapp.store.product

import com.daniel.cabifyapp.base.MvpView
import com.daniel.cabifyapp.store.StoreCart

interface ProductView : MvpView, StoreCart {
    fun showDetails() }