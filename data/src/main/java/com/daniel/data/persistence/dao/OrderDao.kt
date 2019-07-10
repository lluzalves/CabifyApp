package com.daniel.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daniel.data.entity.order.OrderEntity
import io.reactivex.Single

@Dao
interface OrderDao {

    @Query("select * from orders")
    fun getOrders(): Single<List<OrderEntity>>


    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrder(orderId: Long): Single<OrderEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(orderEntity: OrderEntity): Single<Long>
}