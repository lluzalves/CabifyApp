package com.daniel.cabifyapp.store.product

import com.daniel.cabifyapp.base.BasePresenter
import java.math.BigDecimal

class ProductPresenter : BasePresenter<ProductView>() {

    fun convert(price: String): String {
        return price.toBigDecimal().toString()
    }
}