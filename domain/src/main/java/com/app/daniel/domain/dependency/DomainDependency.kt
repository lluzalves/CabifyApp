package com.app.daniel.domain.dependency

import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.repository.IOrderRepository
import com.app.daniel.domain.repository.IRepository
import com.app.daniel.domain.usecase.products.GetProductsUseCase
import com.app.daniel.domain.usecase.products.StartCheckoutUseCase


class DomainDependency {
    private lateinit var productsUseCase: GetProductsUseCase
    private lateinit var orderUseCase: StartCheckoutUseCase

    fun inject(repository: IRepository<Product>) {
        this.productsUseCase = GetProductsUseCase(repository)
    }

    fun inject(repository: IOrderRepository) {
        this.orderUseCase = StartCheckoutUseCase(repository)
    }

    fun getProductUseCase(): GetProductsUseCase? {
        return when {
            ::productsUseCase.isInitialized -> productsUseCase
            else -> null
        }
    }

    fun getOrderUseCase(): StartCheckoutUseCase? {
        return when {
            ::orderUseCase.isInitialized -> orderUseCase
            else -> null
        }
    }

    companion object {
        val SHARED: DomainDependency = DomainDependency()
    }

}