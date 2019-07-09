package com.daniel.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daniel.data.entity.customer.CustomerEntity


@Dao
interface CustomerDao {

    @Query("select * from customer")
    fun getCustomer(): CustomerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customerEntity: CustomerEntity): Long
}