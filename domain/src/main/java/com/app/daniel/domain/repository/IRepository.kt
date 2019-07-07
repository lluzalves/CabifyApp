package com.app.daniel.domain.repository

import com.app.daniel.domain.dto.Product
import io.reactivex.Single

interface IRepository {
    fun retrieveProducts():  Single<List<Product>>
}