package com.daniel.data.entity.product

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName(CODE)
    val code: String,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(PRICE)
    val price: String
) {
    companion object {
        const val CODE = "code"
        const val NAME = "name"
        const val PRICE = "price"
    }
}