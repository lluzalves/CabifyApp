package com.daniel.cabifyapp.dependency

import android.app.Application
import com.app.daniel.domain.dependency.DomainDependency
import com.app.daniel.domain.usecase.products.GetProductsUseCase
import com.daniel.cabifyapp.application.CabifyApp
import com.daniel.data.dependency.DataDependency

class ApplicationDependency {
    val application: Application = CabifyApp.appInstance
    private val repositoryImpl = DataDependency.SHARED.getRepository()

    fun inject() {
        DomainDependency.SHARED.inject(repositoryImpl)
    }

    fun retrieveUseCase() : GetProductsUseCase? {
        return DomainDependency.SHARED.getUseCase()
    }

    companion object {
        val SHARED: ApplicationDependency = ApplicationDependency()
    }
}