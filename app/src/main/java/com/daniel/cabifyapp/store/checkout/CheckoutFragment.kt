package com.daniel.cabifyapp.store.checkout

import android.content.Context
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
import com.daniel.analytics.EventBuilder
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency
import com.daniel.cabifyapp.view.CabifySnackBar
import com.daniel.cabifyapp.view.CustomerTextWatcher
import kotlinx.android.synthetic.main.cabify_bar.*
import kotlinx.android.synthetic.main.checkout_overview_layout.*
import kotlinx.android.synthetic.main.customer_address.*
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*
import kotlinx.android.synthetic.main.payment_layout.*


class CheckoutFragment : BaseFragment(), CheckoutView, StoreCheckout, View.OnClickListener {
    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()
    private val inProgressOrderId = ApplicationDependency.SHARED.getInProgressOrder().id
    private lateinit var checkoutPresenter: CheckoutPresenter
    private var canCheckout: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
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
                if (inProgressOrder.products.isEmpty()) {
                    CabifySnackBar.make(v, getString(R.string.customer_email))
                } else {
                    startCheckoutDialog()
                }
            }
        }
    }

    private fun startCheckoutDialog() {
        val storeCheckout = this
        context?.let { context ->
            MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
                .show {
                    this.title(R.string.checkout_title)
                    this.message(R.string.order_details)
                    this.positiveButton(R.string.confirm) {
                        it.dismiss()
                        showAddressDialog(context, storeCheckout)
                    }
                    this.negativeButton(R.string.dismiss) {
                        it.dismiss()
                    }
                    this.cornerRadius(16f)
                    customView(R.layout.checkout_overview_layout, scrollable = false, horizontalPadding = true)
                    val productAdapter = ProductAdapter(context, inProgressOrder)
                    checkoutItems.adapter = productAdapter
                    setPeekHeight(res = R.dimen.my_peek_height)
                }
        }
    }

    private fun showAddressDialog(context: Context, storeCheckout: StoreCheckout) {
        MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
            .show {
                this.title(R.string.checkout_address)
                this.message(R.string.delivery_info)
                this.positiveButton(R.string.confirm) {
                    if (canCheckout) {
                        inProgressOrder.customer.address = customerAddress.text.toString()
                        inProgressOrder.customer.name = customerName.text.toString()
                        inProgressOrder.customer.email = customerEmail.text.toString()
                        inProgressOrder.customer.cellphone = customerCellphone.text.toString()
                        it.dismiss()
                        showPaymentDialog(context)
                    } else {
                        CabifySnackBar.make(this.view, getString(R.string.please_inform_for_next_step)).show()
                    }
                }
                this.negativeButton(R.string.cancel) {
                    it.dismiss()
                }
                this.cornerRadius(16f)
                customView(R.layout.customer_address, scrollable = false, horizontalPadding = true)
                val textWatcher = CustomerTextWatcher(
                    customerName,
                    customerAddress,
                    customerEmail,
                    customerCellphone,
                    getString(R.string.please_inform),
                    storeCheckout
                )
                customerName.addTextChangedListener(textWatcher)
                customerAddress.addTextChangedListener(textWatcher)
                customerEmail.addTextChangedListener(textWatcher)
                customerCellphone.addTextChangedListener(textWatcher)
                setPeekHeight(res = R.dimen.my_default_peek_height)
                setCancelable(false)
                noAutoDismiss()
                cancelable(false)
                setCanceledOnTouchOutside(false)
                cancelOnTouchOutside(false)
            }
    }

    private fun showPaymentDialog(context: Context) {
        val storeCheckout = this
        MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
            .show {
                this.title(R.string.payment_details)
                this.message(R.string.payment_options)
                this.positiveButton(R.string.pay) {
                    checkoutPresenter.startCheckoutProcess(inProgressOrder)
                    storeCheckout.clear()
                    it.dismiss()
                }
                this.negativeButton(R.string.cancel) {
                    it.dismiss()
                }
                this.cornerRadius(16f)
                customView(R.layout.payment_layout, scrollable = false, horizontalPadding = true)
                setPeekHeight(res = R.dimen.my_default_peek_height)
                setCancelable(false)
                noAutoDismiss()
                cancelable(false)
                setCanceledOnTouchOutside(false)
                cancelOnTouchOutside(false)
                orderTotal.text =
                    String.format(getString(R.string.order_total_amount), inProgressOrder.totalAmount().toString())
                orderBilling.text =
                    String.format(getString(R.string.order_total_billing), inProgressOrder.totalBilling().toString())
                orderDiscount.text = String.format(
                    getString(R.string.order_total_discounts),
                    inProgressOrder.getOrderDiscounts().toString()
                )
                orderChargedAt.text =
                    String.format(getString(R.string.order_charged_at), inProgressOrder.totalBilling().toString())
            }
    }

    override fun allowCheckout() {
        canCheckout = true
    }

    override fun requestedOrder(order: Order) {
        context?.let { context ->
            MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
                .show {
                    this.title(R.string.order_details)
                    this.message(R.string.thanks_for_buying)
                    this.positiveButton(R.string.ok) {
                        it.dismiss()
                    }
                    this.cornerRadius(16f)
                    customView(R.layout.checkout_overview_layout, scrollable = false, horizontalPadding = true)
                    val productAdapter = ProductAdapter(context, inProgressOrder)
                    checkoutItems.adapter = productAdapter
                    setPeekHeight(res = R.dimen.my_default_peek_height)
                }
        }
    }

    override fun onOrderProcessing() {
        view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_is_processing)) }?.show()
    }

    override fun onOrderProcessingError(localizedMessage: String) {
        if (localizedMessage.isEmpty()) {
            view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_error)) }?.show()
        } else {
            view?.let { view -> CabifySnackBar.make(view, localizedMessage) }?.show()

        }
    }

    override fun onOrderCompleted(order: Order) {
        view?.let { view -> CabifySnackBar.make(view, getString(R.string.order_is_completed)) }?.show()
        EventBuilder()
            .createEvent()
            .addProperty(
                EventBuilder.PURCHASE,
                String.format("New purchase completed, billing amount", order.getOrderInfo())
            )
    }


    override fun clear() {
        (productsRecyclerCheckout.adapter as CheckoutAdapter).clearOrder()
        checkoutPresenter.showOrderInfo(inProgressOrderId)
    }


}