package com.daniel.cabifyapp.store.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R

class CheckoutAdapter constructor(private val order: Order, private val storeCheckout: StoreCheckout) :
    RecyclerView.Adapter<CheckoutViewHolder>() {

    lateinit var holder: CheckoutViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_checkout_product_item, null)
        holder = CheckoutViewHolder(view, storeCheckout)
        return holder
    }


    override fun getItemCount(): Int {
        return order.products.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.show(order.products[position])
    }

    fun removeItem(product: Product) {
        val position = order.products.indexOf(product)
        order.products.remove(product)
        notifyItemRemoved(position)
    }

    fun clearOrder() {
        order.products.clear()
        notifyDataSetChanged()
    }
}