package com.daniel.data.adapter

import com.app.daniel.domain.dto.Product
import com.daniel.data.entity.product.ProductEntity

object ProductAdapter {

    fun toProduct(productEntity: ProductEntity) = Product(
        code = productEntity.code,
        name = productEntity.name,
        price = productEntity.price
    )
}