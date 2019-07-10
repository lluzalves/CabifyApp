package com.daniel.data.repository

import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.repository.IOrderRepository
import com.daniel.data.adapter.OrderAdapter
import com.daniel.data.dependency.DataDependency
import io.reactivex.Single

class OrderRepository : IOrderRepository {
    private val database = DataDependency.SHARED.getAppDatabase()

    override fun retrieveList(): Single<List<Order>> {
        return database?.orderDao?.getOrders()?.map { orderEntities -> OrderAdapter().fromDatabase(orderEntities) } as Single<List<Order>>
    }

    override fun insertOrder(order: Order): Single<Long> {
        return database?.orderDao?.insertOrder(OrderAdapter().toOrderEntity(order)) ?: Single.just((-1).toLong())
    }

    override fun retrieveOrder(orderId: Long): Single<Order> {
        return database?.orderDao?.getOrder(orderId)?.map { orderEntity -> OrderAdapter().toOrder(orderEntity) } as Single<Order>
    }

}
