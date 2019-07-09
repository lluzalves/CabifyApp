package com.daniel.cabifyapp.store.checkout

import com.app.daniel.domain.dto.Product

interface StoreCheckout {
    fun removerFromCart(product: Product)
}