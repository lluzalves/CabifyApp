package com.app.daniel.domain.entities

import com.app.daniel.domain.dto.Product
import java.io.Serializable

data class Order(
    val products: ArrayList<Product>,
    val customer: Customer,
    var totalAmount: Int,
    var totalDiscount: Int,
    var billingTotal: Int,
    var createdAt: String,
    var updateAt: String
) : Serializable {
    constructor() : this(ArrayList(), Customer(), 0, 0, 0, "", "")

    fun totalAmount(): Int {
        totalAmount = 0
        products.forEach { product -> totalAmount += product.price.toInt() }
        return totalAmount
    }

    fun applyDiscount(): Int {
        totalDiscount =
            onVoucherItemTryApplyDiscount(minimumItemsQuantity = 2) + onTShirtItemTryApplyDiscount(startQuantity = 3)
        return totalDiscount
    }

    fun totalBilling(): Int {
        billingTotal = totalAmount() - applyDiscount()
        return billingTotal
    }

    private fun onTShirtItemTryApplyDiscount(startQuantity: Int): Int {
        val voucherItems = products.find { product -> product.code == "TSHIRT" }?.quantity ?: 0
        return if (voucherItems >= startQuantity) (1 * voucherItems)
        else 0
    }

    private fun onVoucherItemTryApplyDiscount(minimumItemsQuantity: Int): Int {
        val voucherPrice = products.find { product -> product.code == "VOUCHER" }?.price?.toInt() ?: 0
        val voucherItems = products.find { product -> product.code == "VOUCHER" }?.quantity ?: 0
        return (voucherPrice * voucherItems % minimumItemsQuantity)
    }
}