package com.app.daniel.domain.entities

import java.io.Serializable

data class Customer(
    var address: String,
    var name: String,
    var cellphone: String,
    var email: String
):Serializable{
    constructor() : this("", "", "", "")
}