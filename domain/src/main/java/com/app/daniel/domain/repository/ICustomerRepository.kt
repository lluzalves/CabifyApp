package com.app.daniel.domain.repository
import com.app.daniel.domain.entities.Customer
import com.app.daniel.domain.entities.Order
import io.reactivex.Single

interface ICustomerRepository : IRepository<Customer> {
    fun retrieveCustomers(): Single<List<Customer>>
    fun insertCustomer(): Long
    fun retrieveCustomer(): Single<Customer>
}