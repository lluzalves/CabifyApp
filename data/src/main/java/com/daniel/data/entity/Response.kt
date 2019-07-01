package com.daniel.data.entity

import com.daniel.data.entity.product.Product
import com.daniel.data.common.JsonConverter
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName(PRODUCTS)
    private val products: List<Product>
) : JsonConverter {
    companion object {
        const val PRODUCTS = "Products"
    }
}