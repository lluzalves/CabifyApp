package com.daniel.data.dependency

import com.app.daniel.domain.repository.IRepository
import com.daniel.data.common.CabifyConstants
import com.daniel.data.network.NetworkFactory
import com.daniel.data.repository.RepositoryImpl
import retrofit2.Retrofit

class DataDependency{

    fun getCabifyApiClient():Retrofit{
        return NetworkFactory().httpClient(CabifyConstants.CabifyStoreApi.BASE_URL).newBuilder().build()
    }

    fun getRepository(): IRepository {
        return RepositoryImpl()
    }

    companion object{
        val SHARED: DataDependency
            get() = DataDependency()
    }
}