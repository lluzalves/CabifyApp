package com.daniel.cabifyapp.store.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency
import com.daniel.cabifyapp.view.CabifySnackBar
import kotlinx.android.synthetic.main.cabify_bar.*
import kotlinx.android.synthetic.main.checkout_overview_layout.*
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*


class CheckoutFragment : BaseFragment(), CheckoutView, StoreCheckout, View.OnClickListener {
    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()
    private lateinit var checkoutPresenter: CheckoutPresenter

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
        closeOrderActionButton.setOnClickListener(this)
        checkoutPresenter = CheckoutPresenter()
        checkoutPresenter.attachView(mvpView = this)
    }


    override fun removeFromCart(product: Product) {
        (productsRecyclerCheckout.adapter as CheckoutAdapter).removeItem(product)
    }

    override fun onClick(v: View) {
        when (v) {
            v.closeOrderActionButton -> {
                val storeCheckout = this
                context?.let { context ->
                    MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
                        .show {
                            this.title(R.string.checkout_title)
                            this.message(R.string.order_details)
                            this.positiveButton(R.string.confirm) {
                                checkoutPresenter.startCheckoutProcess(inProgressOrder)
                                storeCheckout.clear()
                            }
                            this.negativeButton(R.string.dismiss) {
                                this.dismiss()
                            }
                            this.cornerRadius(16f)
                            customView(R.layout.checkout_overview_layout, scrollable = false, horizontalPadding = true)
                            val productAdapter = ProductAdapter(context, inProgressOrder)
                            checkoutItems.adapter = productAdapter
                            setPeekHeight(res = R.dimen.my_default_peek_height)
                        }
                }
            }
        }
    }

    private fun showCheckoutDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOrderProcessing() {
        view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_is_processing)) }?.show()
    }

    override fun onOrderProcessingError() {
        view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_error)) }?.show()
    }

    override fun onOrderCompleted() {
        view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_is_completed)) }?.show()
    }

    override fun clear() {
        (productsRecyclerCheckout.adapter as CheckoutAdapter).clearOrder()
    }


}