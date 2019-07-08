package com.app.daniel.domain.dependency

import com.app.daniel.domain.repository.IRepository
import com.app.daniel.domain.usecase.products.GetProductsUseCase


class DomainDependency {
    private lateinit var productsUseCase: GetProductsUseCase

    fun inject(repository: IRepository) {
        this.productsUseCase = GetProductsUseCase(repository)
    }

    fun getUseCase(): GetProductsUseCase? {
        return when {
            ::productsUseCase.isInitialized -> productsUseCase
            else -> null
        }
    }

    companion object {
        val SHARED: DomainDependency = DomainDependency()
    }

}