package com.app.daniel.domain.entities

import com.app.daniel.domain.dto.Product
import java.io.Serializable
import java.math.BigDecimal

data class Order(
    val id : Long,
    val products: ArrayList<Product>,
    val customer: Customer,
    var totalAmount: Int,
    var totalDiscount: Int,
    var billingTotal: Int,
    var createdAt: String,
    var updateAt: String
) : Serializable {
    constructor() : this(System.currentTimeMillis(),ArrayList(), Customer(), 0, 0, 0, "", "")

    fun totalAmount(): Int {
        totalAmount = 0
        products.forEach { product -> totalAmount += product.price.toInt() }
        return totalAmount
    }

    fun totalAmountForProduct(product: Product) : BigDecimal{
        products.find { item -> product.code == item.code }
        return product.price.toBigDecimal() * product.quantity.toBigDecimal()
    }

    fun applyDiscount(): Int {
        totalDiscount =
            onVoucherItemTryApplyDiscount(minimumItemsQuantity = 2) + onTShirtItemTryApplyDiscount(startQuantity = 3)
        return totalDiscount
    }

    fun onTShirtItemTryApplyDiscount(startQuantity: Int): Int {
        val shirtItems = products.find { product -> product.code == Product.Code.TSHIRT }?.quantity ?: 0
        return if (shirtItems >= startQuantity) (1 * shirtItems)
        else 0
    }

    fun onVoucherItemTryApplyDiscount(minimumItemsQuantity: Int): Int {
        val voucherPrice = products.find { product -> product.code == Product.Code.VOUCHER }?.price?.toInt() ?: 0
        val voucherItems = products.find { product -> product.code == Product.Code.VOUCHER }?.quantity ?: 0
        return voucherPrice * (voucherItems / minimumItemsQuantity)
    }
}