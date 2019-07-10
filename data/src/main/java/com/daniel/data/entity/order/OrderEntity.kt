package com.daniel.data.entity.order

import androidx.annotation.NonNull
import androidx.room.*
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Customer
import com.daniel.data.entity.BaseEntity
import com.google.gson.Gson

@Entity(tableName = OrderEntity.NAME)
data class OrderEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Columns.ID)
    override var id: Long = System.currentTimeMillis(),

    @ColumnInfo(name = Columns.CREATED_AT)
    override var createdAt: String,

    @ColumnInfo(name = Columns.UPDATED_AT)
    override var updatedAt: String,

    @ColumnInfo(name = Columns.ORDER_TOTAL_AMOUNT)
    var totalAmount: Double,

    @ColumnInfo(name = Columns.ORDER_DISCOUNTS)
    var orderDiscounts: Double,

    @ColumnInfo(name = Columns.ORDER_BILLING_AMOUNT)
    var billingAmount: Double,

    @TypeConverters(ProductConverter::class)
    val products: List<Product>,

    @Embedded
    val customer: Customer
) : BaseEntity {

    companion object {
        const val NAME = "orders"

        object Columns {
            const val ID = "id"
            const val PRODUCT = "product"
            const val CUSTOMER = "customer"
            const val ORDER_TOTAL_AMOUNT = "total_amount"
            const val ORDER_DISCOUNTS = "order_discounts"
            const val ORDER_BILLING_AMOUNT = "order_billing"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
        }
    }

    class ProductConverter {
        @TypeConverter
        fun listToJson(value: List<Product>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        fun jsonToList(value: String): List<Product>? {

            val products = Gson().fromJson(value, Array<Product>::class.java) as Array<Product>
            return products.toList()
        }
    }
}
