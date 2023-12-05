package com.shop.entity

import jakarta.persistence.*

@Entity
data class ActivityArea(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var name: String,

    @ManyToMany(
        mappedBy = "activityAreas"
    )
    var companies: MutableList<Company> = mutableListOf()
)
