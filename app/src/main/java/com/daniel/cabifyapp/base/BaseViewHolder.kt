package com.daniel.cabifyapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> protected constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun show(model: T)

    abstract fun onErrorState()

    abstract fun showItem()

    abstract fun hideItem()

    abstract fun blockItem()

    abstract fun setClickListener(itemView : View)

    abstract fun clearClickListener(itemView : View)

}