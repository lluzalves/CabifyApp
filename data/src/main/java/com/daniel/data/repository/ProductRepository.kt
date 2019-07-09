package com.daniel.data.repository

import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.repository.IRepository
import com.app.daniel.salesforce.commons.applyScheduler
import com.daniel.data.dependency.DataDependency
import com.daniel.data.services.ProductsService
import io.reactivex.Single

class ProductRepository : IRepository<Product> {
    private val httpClient = DataDependency.SHARED.getCabifyApiClient()

    override fun retrieveList(): Single<List<Product>> {
        return httpClient.create(ProductsService::class.java)
            .getProducts()
            .map { response -> return@map response.mapToProductList() }
            .applyScheduler()
    }

  }