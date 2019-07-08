package com.daniel.data.repository

import com.app.daniel.salesforce.commons.applyScheduler
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.repository.IRepository
import com.daniel.data.dependency.DataDependency
import com.daniel.data.services.ProductsService
import io.reactivex.Single

class RepositoryImpl : IRepository {

    private val httpClient = DataDependency.SHARED.getCabifyApiClient()

    override fun retrieveProducts(): Single<List<Product>> {
        return httpClient.create(ProductsService::class.java)
            .getProducts()
            .map { response -> return@map response.mapToProductList() }
            .applyScheduler()
    }

}