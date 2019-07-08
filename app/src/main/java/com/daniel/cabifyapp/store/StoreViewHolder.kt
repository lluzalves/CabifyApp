package com.daniel.cabifyapp.store

import android.view.View
import androidx.core.content.ContextCompat
import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseViewHolder
import kotlinx.android.synthetic.main.cell_product_item.view.*

class StoreViewHolder constructor(itemView: View) : BaseViewHolder<Product>(itemView) {

    override fun show(model: Product) {
        itemView.product_name.text = model.name
        itemView.product_price.text = model.price
        setProductImage(model.code)
    }

    private fun setProductImage(code: String) {
        when (code) {
            "VOUCHER" -> {
                itemView.product_item.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.ic_cabify_logo_color_rgb))
            }
            "TSHIRT" -> {
                itemView.product_item.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.mipmap.ic_shirts))
            }
            "MUG" -> {
                itemView.product_item.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.mipmap.ic_mugs))
            }
            else -> {
                itemView.product_item.setImageDrawable(ContextCompat.getDrawable(itemView.context,android.R.drawable.stat_notify_error))
            }

        }
    }

    override fun onErrorState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun blockItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClickListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearClickListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}