package com.app.daniel.domain.entities

import com.app.daniel.domain.dto.Product
import java.io.Serializable
import java.math.BigDecimal
import java.util.function.BiFunction

data class Order(
    val id: Long,
    val products: ArrayList<Product>,
    val customer: Customer,
    var totalAmount: Double,
    var totalDiscount: Double,
    var billingTotal: Double,
    var createdAt: String,
    var updateAt: String
) : Serializable {
    constructor() : this(System.currentTimeMillis(), ArrayList(), Customer(), 0.0, 0.0, 0.0, "", "")

    fun totalAmount(): Double {
        totalAmount = 0.0
        products.forEach { product -> totalAmount += (product.price.toDouble() * product.quantity) }
        return totalAmount
    }

    fun totalAmountForProduct(product: Product): Double {
        products.find { item -> product.code == item.code }
        return product.price.toDouble() * product.quantity
    }

    fun getOrderDiscounts(): Double {
        return onVoucherItemTryApplyDiscount(minimumItemsQuantity = 2) + onTShirtItemTryApplyDiscount(startQuantity = 3)
    }

    fun totalBilling(): Double {
        return totalAmount - getOrderDiscounts()
    }

    fun onTShirtItemTryApplyDiscount(startQuantity: Int): Int {
        val shirtItems = products.find { product -> product.code == Product.Code.TSHIRT }?.quantity ?: 0
        return if (shirtItems >= startQuantity) (1 * shirtItems)
        else 0
    }

    fun onVoucherItemTryApplyDiscount(minimumItemsQuantity: Int): Double {
        val voucherPrice = products.find { product -> product.code == Product.Code.VOUCHER }?.price?.toDouble() ?: 0.0
        val voucherItems = products.find { product -> product.code == Product.Code.VOUCHER }?.quantity ?: 0
        return voucherPrice * (voucherItems / minimumItemsQuantity).toDouble()
    }

    fun getOrderInfo(): String {
        return "Purchase total: ".plus(this.totalAmount).plus(" with a total of discounts of: ")
            .plus(this.totalDiscount).plus(" and billed to customer at a total of: ").plus(this.billingTotal)
    }
}