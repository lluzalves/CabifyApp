package com.daniel.cabifyapp.dependency

import android.app.Application
import com.app.daniel.domain.dependency.DomainDependency
import com.app.daniel.domain.entities.Order
import com.app.daniel.domain.usecase.products.GetProductsUseCase
import com.daniel.cabifyapp.MainActivity
import com.daniel.cabifyapp.application.CabifyApp
import com.daniel.data.dependency.DataDependency

class ApplicationDependency {
    private val application: Application = CabifyApp.appInstance
    private val repositoryImpl = DataDependency.SHARED.getRepository()
    private lateinit var mainActivity : MainActivity
    private val inProgressOrder = Order()

    fun inject() {
        DomainDependency.SHARED.inject(repositoryImpl)
        DataDependency.SHARED.inject(application)
    }
    fun inject(mainActivity: MainActivity){
        this.mainActivity = mainActivity
    }


    fun retrieveUseCase(): GetProductsUseCase? {
        return DomainDependency.SHARED.getUseCase()
    }

    fun getActivity(): MainActivity {
        return mainActivity
    }

    fun getInProgressOrder() : Order{
        return inProgressOrder
    }

    companion object {
        val SHARED: ApplicationDependency = ApplicationDependency()
    }
}