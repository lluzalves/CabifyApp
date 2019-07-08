package com.daniel.cabifyapp.store

import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.base.MvpView

interface StoreView: MvpView {
    fun getProducts(products : List<Product>)
}