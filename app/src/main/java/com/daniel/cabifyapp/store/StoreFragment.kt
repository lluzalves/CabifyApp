package com.daniel.cabifyapp.store


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency

class StoreFragment : BaseFragment(), StoreView {

    lateinit var presenter: StorePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApplicationDependency.SHARED.inject()
        presenter = StorePresenter()
        presenter.attachView(this)
        presenter.requestStoreProducts()
    }

    override fun getProducts(products: List<Product>) {

    }


}
