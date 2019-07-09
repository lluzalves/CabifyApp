package com.daniel.cabifyapp.store.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.R
import com.daniel.cabifyapp.base.BaseFragment
import com.daniel.cabifyapp.dependency.ApplicationDependency


class CheckoutFragment : BaseFragment() {

    var inProgressOrder: Order = ApplicationDependency.SHARED.getInProgressOrder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initializeUiState(product: Product) {

    }
}