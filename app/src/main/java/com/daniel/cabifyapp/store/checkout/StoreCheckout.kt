package com.daniel.cabifyapp.store.checkout

import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order

interface StoreCheckout {
    fun removeFromCart(product: Product)
    fun allowCheckout()
    fun blockCheckout()
    fun requestedOrder(order: Order)
    fun clear()
}