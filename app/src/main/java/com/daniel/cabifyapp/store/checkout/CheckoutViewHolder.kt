package com.daniel.cabifyapp.store.checkout

import android.view.View
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseViewHolder
import com.daniel.cabifyapp.commons.ProductDrawables
import com.daniel.cabifyapp.dependency.ApplicationDependency
import kotlinx.android.synthetic.main.cell_checkout_product_item.view.*


class CheckoutViewHolder constructor(itemView: View, private val storeCheckout: StoreCheckout) :
    BaseViewHolder<Product>(itemView),
    View.OnClickListener {
    private lateinit var product: Product
    private val order = ApplicationDependency.SHARED.getInProgressOrder()

    override fun show(model: Product) {
        product = model
        itemView.productCheckoutName.text = product.name
        itemView.productCheckoutPrice.text = product.price
        itemView.productCheckoutQuantity.text = product.quantity.toString()
        setProductImage(product.code)
        setClickListener(itemView.removeCart)
    }

    private fun setProductImage(code: String) {
        ProductDrawables.onProductCodeSetBackground(code, itemView.productItemCheckout)
    }

    override fun setClickListener(itemView: View) {
        itemView.setOnClickListener(this)
    }

    override fun clearClickListener(itemView: View) {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            itemView.removeCart -> {
                showRemoveProductDialog()
            }
        }
    }

    private fun showRemoveProductDialog() {
        MaterialDialog(itemView.context, BottomSheet(LayoutMode.WRAP_CONTENT))
            .show {
                this.title(R.string.warning)
                this.message(R.string.warning_removing_item)
                this.positiveButton(R.string.confirm) {
                    storeCheckout.removeFromCart(product)
                }
                this.negativeButton(R.string.cancel) {
                    it.dismiss()
                }
                this.cornerRadius(16f)
                setPeekHeight(res = R.dimen.my_default_peek_height)
            }
    }

}