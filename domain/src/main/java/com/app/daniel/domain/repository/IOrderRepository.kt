package com.app.daniel.domain.repository
import com.app.daniel.domain.entities.Order
import io.reactivex.Single

interface IOrderRepository : IRepository<Order> {
    fun insertOrder(order: Order): Single<Long>
    fun retrieveOrder(orderId : Long): Single<Order>
}