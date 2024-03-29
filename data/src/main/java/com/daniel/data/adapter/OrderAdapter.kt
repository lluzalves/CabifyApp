package com.daniel.data.adapter

import com.app.daniel.domain.entities.Order
import com.daniel.data.entity.order.OrderEntity

class OrderAdapter {
    fun toOrder(orderEntity: OrderEntity) = Order(
        id = orderEntity.id,
        products = ArrayList(orderEntity.products),
        customer = orderEntity.customer,
        totalAmount = orderEntity.totalAmount,
        totalDiscount = orderEntity.orderDiscounts,
        billingTotal = orderEntity.billingAmount,
        createdAt = orderEntity.createdAt,
        updateAt = orderEntity.updatedAt
    )

    fun toOrderEntity(order: Order) = OrderEntity(
        id = System.currentTimeMillis(),
        products = order.products.toList(),
        customer = order.customer,
        totalAmount = order.totalAmount,
        billingAmount = order.billingTotal,
        orderDiscounts = order.totalDiscount,
        createdAt = System.currentTimeMillis().toString(),
        updatedAt = System.currentTimeMillis().toString()
    )


    fun fromDatabase(orderEntities: List<OrderEntity>): List<Order> =
        orderEntities.map { orderEntity -> toOrder(orderEntity) }
            .toList()

}