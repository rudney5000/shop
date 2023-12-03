package com.shop.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ActivityArea(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,
    private var name: String,

//    private var companies: List<Company>
)
