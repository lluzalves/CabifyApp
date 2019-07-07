package com.app.daniel.domain.usecase.products

import com.app.daniel.salesforce.commons.async
import com.app.daniel.salesforce.domain.usecase.base.UseCase
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.repository.IRepository
import io.reactivex.Single


class GetProductsUseCase constructor(private val repository : IRepository) : UseCase<List<Product>>()  {

    override fun buildUseCase(): Single<List<Product>> {
        return repository.retrieveProducts().async()
    }

}
