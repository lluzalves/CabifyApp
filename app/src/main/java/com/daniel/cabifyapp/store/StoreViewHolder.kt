package com.daniel.cabifyapp.store

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.app.daniel.domain.dto.Product
import com.app.daniel.salesforce.commons.Keys
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseViewHolder
import com.daniel.cabifyapp.commons.ProductDrawables
import kotlinx.android.synthetic.main.cell_product_item.view.*
import kotlinx.android.synthetic.main.product_quantity.*


class StoreViewHolder constructor(itemView: View, private val storeCart: StoreCart) : BaseViewHolder<Product>(itemView),
    View.OnClickListener {
    private lateinit var product: Product

    override fun show(model: Product) {
        product = model
        itemView.productName.text = product.name
        itemView.productPrice.text = product.price
        setProductImage(product.code)
        if (product.quantity > 0) applyOnProgressCartState()
        setClickListener(itemView.cart)
        setClickListener(itemView.productItem)
        setClickListener(itemView.productName)
        setClickListener(itemView.productPrice)
    }

    private fun setProductImage(code: String) {
        ProductDrawables.onProductCodeSetBackground(code, itemView.productItem)
    }

    override fun setClickListener(itemView: View) {
        itemView.setOnClickListener(this)
    }

    override fun clearClickListener(itemView: View) {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            itemView.cart -> {
                handleCartState()
            }
            itemView.productItem,
            itemView.productName,
            itemView.productPrice -> {
                val bundle = Bundle()
                bundle.putSerializable(Keys.PRODUCT, product)
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_productFragment, bundle)
            }
        }
    }

    private fun handleCartState() {
        if (itemView.cart.tag == Keys.DEFAULT) {
            showQuantityDialog()
        } else {
            itemView.cart.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_add_shopping_cart
                )
            )
            storeCart.removerFromCart(product)
            itemView.cart.tag = Keys.DEFAULT
        }
    }

    private fun showQuantityDialog() {
        MaterialDialog(itemView.context, BottomSheet(LayoutMode.WRAP_CONTENT))
            .show {
                this.title(R.string.add_product_quantity)
                this.message(R.string.inform_product_quantity)
                this.positiveButton(R.string.confirm) {
                    applyOnProgressCartState()
                    product.quantity = productQuantity.text.toString().toInt()
                    storeCart.addToCart(product)
                }
                this.cornerRadius(16f)
                customView(R.layout.product_quantity, scrollable = false, horizontalPadding = true)
                setPeekHeight(res = R.dimen.my_default_peek_height)
            }
    }

    private fun applyOnProgressCartState() {
        itemView.cart.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_shopping_basket
            )
        )
        itemView.cart.tag = Keys.ON_CART
    }
}