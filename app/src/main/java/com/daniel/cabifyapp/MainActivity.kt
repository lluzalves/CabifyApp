package com.daniel.cabifyapp

import android.os.Bundle
import com.app.daniel.domain.dto.Product
import com.app.daniel.domain.entities.Order
import com.daniel.cabifyapp.base.BaseActivity
import com.daniel.cabifyapp.dependency.ApplicationDependency
import com.daniel.cabifyapp.store.StoreCart

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        ApplicationDependency.SHARED.inject()
        ApplicationDependency.SHARED.inject(this)
    }

}
