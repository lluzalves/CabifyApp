package com.daniel.data.entity.order

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Customer
import com.daniel.data.entity.BaseEntity

@Entity(tableName = OrderEntity.NAME)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = Columns.ID)
    override var id: Int?,

    @ColumnInfo(name = Columns.CREATED_AT)
    override var createdAt: String,

    @ColumnInfo(name = Columns.UPDATED_AT)
    override var updatedAt: String,

    @ColumnInfo(name = Columns.ORDER_TOTAL_AMOUNT)
    var totalAmount: Int,

    @ColumnInfo(name = Columns.ORDER_DISCOUNTS)
    var orderDiscounts: Int,

    @ColumnInfo(name = Columns.ORDER_BILLING_AMOUNT)
    var billingAmount: Int,

    @ColumnInfo(name = Columns.PRODUCT)
    @Embedded
    val products: ArrayList<Product>,

    @ColumnInfo(name = Columns.CUSTOMER)
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
}