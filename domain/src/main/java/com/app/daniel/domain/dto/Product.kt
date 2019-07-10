package com.app.daniel.domain.dto

import java.io.Serializable

data class Product(
    val code: String,
    val name: String,
    val price: String,
    var quantity: Int
) : Serializable {
    object Code {
        const val TSHIRT = "TSHIRT"
        const val VOUCHER = "VOUCHER"
        const val MUG = "MUG"
    }
}