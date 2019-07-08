package com.daniel.data.entity

import com.app.daniel.domain.dto.Product
import com.daniel.data.adapter.ProductAdapter
import com.daniel.data.entity.product.ProductEntity
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName(PRODUCTS)
    private val productEntities: List<ProductEntity>
) {

    fun mapToProductList(): List<Product> {
        val product = ArrayList<Product>()
        productEntities.mapTo(destination = product) { productEntity -> ProductAdapter.toProduct(productEntity) }
        return product.toList()
    }

    companion object {
        const val PRODUCTS = "products"
    }
}