package com.daniel.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.daniel.data.entity.product.ProductEntity
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("select * from product")
    fun getProducts(): Single<List<ProductEntity>>


    @Query("select * from product where id = :productId")
    fun getProduct(productId: Long): Single<ProductEntity>

}