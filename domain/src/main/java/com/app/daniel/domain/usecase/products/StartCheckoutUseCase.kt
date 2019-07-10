package com.app.daniel.domain.usecase.products

import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.repository.IOrderRepository
import com.app.daniel.domain.usecase.base.UseCase
import com.app.daniel.salesforce.commons.applyScheduler
import io.reactivex.Single

class StartCheckoutUseCase constructor(private val repository: IOrderRepository) : UseCase<List<Order>>() {

    override fun buildUseCase(): Single<List<Order>> {
        return repository.retrieveList().applyScheduler()
    }

    fun buildInsertOrderUseCase(order: Order): Single<Long> {
        order.createdAt = System.currentTimeMillis().toString()
        order.updateAt = System.currentTimeMillis().toString()
        return repository.insertOrder(order)
    }

    fun buildRetrieveOrderUseCase(orderId: Long): Single<Order> {
        return repository.retrieveOrder(orderId).applyScheduler()
    }

    fun buildRetrieveAllOrders() : Single<List<Order>> {
        return repository.retrieveOrders().applyScheduler()

    }
}