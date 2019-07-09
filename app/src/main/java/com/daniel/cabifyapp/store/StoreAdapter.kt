package com.daniel.cabifyapp.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.daniel.domain.dto.Product
import com.daniel.cabifyapp.R

class StoreAdapter constructor(private val products: ArrayList<Product>, private val storeCart: StoreCart) : RecyclerView.Adapter<StoreViewHolder>() {

    lateinit var holder: StoreViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_product_item, null)
        holder = StoreViewHolder(view,storeCart)
        return holder
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.show(products[position])
    }

    fun removeQuantityFromProductItem(product: Product) {
        val position = products.indexOf(product)
        products[position].quantity = 0
    }
}