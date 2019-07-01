package com.daniel.data.services

import com.daniel.data.common.CabifyConstants
import com.daniel.data.entity.Response
import io.reactivex.Single
import retrofit2.http.GET

interface ProductsService {

    @GET(CabifyConstants.CabifyStoreApi.BASE_URL)
    fun getProducts() : Single<Response>
}