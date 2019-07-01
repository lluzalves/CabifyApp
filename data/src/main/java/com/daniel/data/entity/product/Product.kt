package com.daniel.data.entity.product

import com.daniel.data.common.JsonConverter
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName(CODE)
    val code: String,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(PRICE)
    val price: String
) : JsonConverter {
    companion object {
        const val CODE = "code"
        const val NAME = "name"
        const val PRICE = "price"
    }
}