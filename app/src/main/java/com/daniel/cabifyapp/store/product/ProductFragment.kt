package com.daniel.cabifyapp.store.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.app.daniel.salesforce.commons.Keys
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.commons.ProductDrawables
import com.daniel.cabifyapp.dependency.ApplicationDependency
import com.daniel.cabifyapp.view.CabifySnackBar
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.product_quantity.*

class ProductFragment : BaseFragment(), ProductView, View.OnClickListener {

    private lateinit var product: Product
    private lateinit var productPresenter: ProductPresenter
    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        if (arguments != null) {
            product = arguments?.getSerializable(Keys.PRODUCT) as Product
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productPresenter = ProductPresenter()
        productPresenter.attachView(this)
        productBasketAction.setOnClickListener(this)
        initializeUiState(product)
    }

    private fun initializeUiState(product: Product) {
        name.text = product.name
        price.text = productPresenter.convert(product.price)
        ProductDrawables.onProductCodeSetBackground(product.code, productView)
        productBasketAction.text = if (product.quantity > 0) {
            getString(R.string.remove_from_basket)
        } else {
            getString(R.string.add_to_basket)
        }

    }

    override fun showDetails() {
        initializeUiState(product)
    }

    override fun addToCart(product: Product) {
        inProgressOrder.products.add(product)
        productBasketAction.text = getString(R.string.remove_from_basket)

    }

    override fun removerFromCart(product: Product) {
        inProgressOrder.products.remove(product)
        productBasketAction.text = getString(R.string.add_to_basket)
    }

    override fun clear() {
        inProgressOrder.products.clear()
    }

    override fun checkout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View) {
        when(v){
            productBasketAction -> {
                if(product.quantity>0){
                    product.quantity = 0
                    removerFromCart(product)
                    view?.let { CabifySnackBar.make(it,String().format(getString(R.string.removed_from_basket),product.name)).show() }
                }else{
                    showQuantityDialog()
                }
            }
        }
    }

    private fun showQuantityDialog() {
        val view = view
        context?.let {context ->
            MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT))
                .show {
                    this.title(R.string.add_product_quantity)
                    this.message(R.string.inform_product_quantity)
                    this.positiveButton(R.string.confirm) {
                        product.quantity = productQuantity.text.toString().toInt()
                        addToCart(product)
                        view?.let { CabifySnackBar.make(it,String().format(getString(R.string.added_to_basket),product.name)).show() }

                    }
                    this.cornerRadius(16f)
                    customView(R.layout.product_quantity, scrollable = false, horizontalPadding = true)
                    setPeekHeight(res = R.dimen.my_default_peek_height)
                }
        }
    }


}