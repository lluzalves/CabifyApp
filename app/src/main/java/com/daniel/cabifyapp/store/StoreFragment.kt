package com.daniel.cabifyapp.store


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency
import kotlinx.android.synthetic.main.cabify_bar.*
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_home.*

class StoreFragment : BaseFragment(), StoreView, View.OnClickListener {

    lateinit var presenter: StorePresenter
    lateinit var products: ArrayList<Product>
    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StorePresenter()
        presenter.attachView(mvpView = this)
        presenter.currentCart(storeCart = this)
        presenter.requestStoreProducts()
        checkout.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        resumeIfAnyInProgressOrder()
    }

    private fun resumeIfAnyInProgressOrder() {
        inProgressOrder.products.forEach { product -> checkout_items_total.text = addToBasketItems(product) }
    }


    override fun getProducts(products: List<Product>) {
        this.products = ArrayList(products)
        for (product in inProgressOrder.products) {
            products.find { item -> item.code == product.code }?.quantity = product.quantity
        }
    }

    override fun onCompleted() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        productsRecycler.layoutManager = staggeredGridLayoutManager
        productsRecycler.setHasFixedSize(true)
        registerForContextMenu(productsRecycler)
        productsRecycler.adapter = StoreAdapter(products, this)
        dismissloadingView()
    }

    private fun dismissloadingView() {
        progress.visibility = View.GONE
        productsRecycler.visibility = View.VISIBLE
    }

    override fun addToCart(product: Product) {
        inProgressOrder.products.add(product)
        checkout_items_total.text = addToBasketItems(product)
    }

    private fun addToBasketItems(product: Product): String {
        var currentItems: Int = if (checkout_items_total.text.toString().isEmpty()) {
            0
        } else {
            checkout_items_total.text.toString().toInt()
        }
        currentItems = (currentItems + product.quantity)
        return currentItems.toString()
    }

    override fun removerFromCart(product: Product) {
        checkout_items_total.text = removeFromBasketItems(product)
        inProgressOrder.products.remove(product)
        (productsRecycler.adapter as StoreAdapter).removeQuantityFromProductItem(product)
    }

    private fun removeFromBasketItems(product: Product): String {
        var currentItems: Int = checkout_items_total.text.toString().toInt()
        currentItems = (currentItems - product.quantity)

        return currentItems.toString()
    }

    override fun clear() {
        inProgressOrder.products.clear()
    }


    override fun checkout() {
        view?.let { view -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productFragment) }
    }

    override fun onClick(v: View) {
        when (v) {
            checkout -> Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_checkoutFragment)
        }

    }
}
