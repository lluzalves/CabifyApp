package com.daniel.cabifyapp

import android.os.Bundle
import com.daniel.cabifyapp.base.BaseActivity
import com.daniel.cabifyapp.dependency.ApplicationDependency

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}
