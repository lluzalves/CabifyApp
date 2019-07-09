package com.daniel.cabifyapp.store

import com.app.daniel.domain.dto.Product

interface StoreCart {
    fun addToCart(product : Product)
    fun removerFromCart(product: Product)
    fun clear()
    fun checkout()
}