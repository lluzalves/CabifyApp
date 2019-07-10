package com.daniel.data.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = ProductEntity.DBNAME)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id : Int,
    @SerializedName(CODE)
    val code: String,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(PRICE)
    val price: String,
    var quantity: Int
) {
    companion object {
        const val DBNAME = "product"
        const val CODE = "code"
        const val NAME = "name"
        const val PRICE = "price"
    }
}