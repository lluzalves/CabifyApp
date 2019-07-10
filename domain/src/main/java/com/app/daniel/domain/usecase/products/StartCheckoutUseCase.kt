package com.app.daniel.domain.usecase.products

import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.repository.IOrderRepository
import com.app.daniel.domain.usecase.base.UseCase
import com.app.daniel.salesforce.commons.applyScheduler
import io.reactivex.Single

class StartCheckoutUseCase constructor(private val repository: IOrderRepository): UseCase<List<Order>>() {

    override fun buildUseCase(): Single<List<Order>> {
        return repository.retrieveList().applyScheduler()
    }

    fun buildInsertOrderUseCase(order : Order): Single<Long> {
        return repository.insertOrder(order)
    }

    fun buildRetrieveOrderUseCasae(order: Order) : Single<Order>{
        return repository.retrieveOrder(order.id).applyScheduler()
    }
}