package com.shop.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
data class ActivityArea(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,

    @ManyToMany(
        mappedBy = "activityAreas"
    )
    var companies: MutableList<Company> = mutableListOf(),

    @CreationTimestamp
    var created: Timestamp? = null,
)
