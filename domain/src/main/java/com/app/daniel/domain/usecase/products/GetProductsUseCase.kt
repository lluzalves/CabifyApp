package com.app.daniel.domain.usecase.products

import com.app.daniel.salesforce.commons.applyScheduler
import com.app.daniel.domain.usecase.base.UseCase
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.repository.IRepository
import io.reactivex.Single


class GetProductsUseCase constructor(private val repository : IRepository<Product>) : UseCase<List<Product>>()  {

    override fun buildUseCase(): Single<List<Product>> {
        return repository.retrieveList().applyScheduler()
    }

}
