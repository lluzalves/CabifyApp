package com.daniel.data.dependency

import android.app.Application
import com.daniel.data.common.CabifyConstants
import com.daniel.data.network.NetworkFactory
import com.daniel.data.persistence.AppDatabase
import com.daniel.data.repository.ProductRepository
import retrofit2.Retrofit

class DataDependency{

    private lateinit var application : Application;

    fun getCabifyApiClient():Retrofit{
        return NetworkFactory().httpClient(CabifyConstants.CabifyStoreApi.BASE_URL).newBuilder().build()
    }

    fun getRepository(): ProductRepository {
        return ProductRepository()
    }

    fun inject(application: Application){
        this.application = application
    }

    fun getAppDatabase(): AppDatabase? {
        return  AppDatabase.getDatabase(application.applicationContext)
    }

    companion object{
        val SHARED: DataDependency = DataDependency()
    }
}