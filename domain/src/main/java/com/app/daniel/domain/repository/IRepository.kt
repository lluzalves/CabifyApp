package com.app.daniel.domain.repository

import io.reactivex.Single

interface IRepository<T> {
    fun retrieveList(): Single<List<T>>
}