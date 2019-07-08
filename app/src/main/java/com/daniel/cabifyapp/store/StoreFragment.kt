package com.daniel.cabifyapp.store


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency
import kotlinx.android.synthetic.main.fragment_home.*

class StoreFragment : BaseFragment(), StoreView {

    lateinit var presenter: StorePresenter
    lateinit var products: List<Product>

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
        this.products = products
    }

    override fun onCompleted() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE)
        productsRecycler.layoutManager = staggeredGridLayoutManager
        productsRecycler.setHasFixedSize(true)
        val context = this.context
        registerForContextMenu(productsRecycler)
        productsRecycler.adapter = StoreAdapter(products)
    }


}
