package com.daniel.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daniel.data.entity.customer.CustomerEntity
import com.daniel.data.entity.order.OrderEntity
import com.daniel.data.persistence.dao.CustomerDao
import com.daniel.data.persistence.dao.OrderDao

@Database(entities = [CustomerEntity::class, OrderEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val customerDao: CustomerDao
    abstract val orderDao: OrderDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, "app_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}