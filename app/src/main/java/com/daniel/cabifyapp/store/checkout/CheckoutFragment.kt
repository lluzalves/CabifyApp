package com.daniel.cabifyapp.store.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency
import kotlinx.android.synthetic.main.cabify_bar.*
import kotlinx.android.synthetic.main.fragment_checkout.*


class CheckoutFragment : BaseFragment(), CheckoutView, StoreCheckout {

    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkout_items_total.visibility = View.INVISIBLE
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        productsRecyclerCheckout.layoutManager = staggeredGridLayoutManager
        productsRecyclerCheckout.setHasFixedSize(true)
        registerForContextMenu(productsRecyclerCheckout)
        productsRecyclerCheckout.adapter = CheckoutAdapter(inProgressOrder, this)
    }


    override fun removerFromCart(product: Product) {
        (productsRecyclerCheckout.adapter as CheckoutAdapter).removeItem(product)
    }

}