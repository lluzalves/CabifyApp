package com.daniel.cabifyapp.dependency

import com.app.daniel.domain.dependency.DomainDependency
import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.usecase.products.GetProductsUseCase
import com.app.daniel.domain.usecase.products.StartCheckoutUseCase
import com.daniel.cabifyapp.MainActivity
import com.daniel.cabifyapp.application.CabifyApp
import com.daniel.data.dependency.DataDependency
import com.daniel.data.repository.OrderRepository

class ApplicationDependency {
    private val productRepository = DataDependency.SHARED.getProductRepository()
    private lateinit var mainActivity : MainActivity
    private lateinit var orderRepository : OrderRepository
    private val inProgressOrder = Order()

    fun inject() {
        DomainDependency.SHARED.inject(productRepository)
        DataDependency.SHARED.inject(CabifyApp.appInstance)
        orderRepository = DataDependency.SHARED.getOrderRepository()
        DomainDependency.SHARED.inject(orderRepository)
    }
    fun inject(mainActivity: MainActivity){
        this.mainActivity = mainActivity
    }


    fun retrieveProductUseCase(): GetProductsUseCase? {
        return DomainDependency.SHARED.getProductUseCase()
    }

    fun retrieveOrderUseCase() : StartCheckoutUseCase?{
        return DomainDependency.SHARED.getOrderUseCase()
    }

    fun getActivity(): MainActivity {
        return mainActivity
    }

    fun getInProgressOrder() : Order{
        return inProgressOrder
    }

    companion object {
        val SHARED: ApplicationDependency = ApplicationDependency()
    }
}