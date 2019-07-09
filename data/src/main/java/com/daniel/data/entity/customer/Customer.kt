package com.daniel.data.entity.customer

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniel.data.entity.BaseEntity


@Entity(tableName = CustomerEntity.NAME)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = Columns.ID)
    override var id: Int,

    @ColumnInfo(name = Columns.CREATED_AT)
    override var createdAt: String,

    @ColumnInfo(name = Columns.UPDATED_AT)
    override var updatedAt: String,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.EMAIL)
    val email: String
) : BaseEntity {

    companion object {
        const val NAME = "customer"

        object Columns {
            const val ID = "id"
            const val NAME = "name"
            const val EMAIL = "email"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
        }
    }
}