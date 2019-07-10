package com.daniel.cabifyapp.store.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.commons.ProductDrawables
import kotlinx.android.synthetic.main.cell_checkout_item.view.*

class ProductAdapter constructor(private val context: Context, private val dataSource: Order) :
    BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var quantity: TextView
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var discount: TextView
    private lateinit var total: TextView
    private lateinit var avatar : ImageView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.cell_checkout_item, parent, false)
        name = rowView.product_name
        quantity = rowView.product_quantity
        discount = rowView.discount
        price = rowView.prod_price
        total = rowView.total
        avatar =  rowView.product_imv
        val product = getItem(position)
        name.text = String.format(context.getString(R.string.product), product.name)
        quantity.text = String.format(context.getString(R.string.quantity), product.quantity.toString())
        price.text = String.format(context.getString(R.string.prod_price), product.price)
        total.text =
            String.format(context.getString(R.string.total), dataSource.totalAmountForProduct(product).toString())
        when {
            product.code == Product.Code.VOUCHER -> discount.text = String.format(
                context.getString(R.string.discount_amount),
                dataSource.onVoucherItemTryApplyDiscount(minimumItemsQuantity = 2).toString()
            )
            product.code == Product.Code.TSHIRT -> discount.text =
                String.format(
                    context.getString(R.string.discount_amount),
                    dataSource.onTShirtItemTryApplyDiscount(startQuantity = 3).toString()
                )
            product.code == Product.Code.MUG -> discount.text = String.format(
                context.getString(R.string.discount_amount), context.getString(R.string.not_applicable)
            )
        }
        ProductDrawables.onProductCodeSetBackground(product.code,avatar)
        return rowView
    }

    override fun getItem(position: Int): Product {
        return dataSource.products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.products.size
    }
}