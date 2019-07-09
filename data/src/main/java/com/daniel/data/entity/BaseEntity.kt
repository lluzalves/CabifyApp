package com.daniel.data.entity

interface BaseEntity {
    val id: Int?
    val createdAt: String
    var updatedAt: String
}