package com.shop.entity

import jakarta.persistence.*

@Entity
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,
    private var description: String,
    private var phone: String,
    private var address: String,
    private var name: String,
    private var ref: String,
    private var email: String,

)
