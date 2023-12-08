package com.shop.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,

    var name:String,

    @OneToMany(
        mappedBy = "countries",
        cascade =[CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var cities: MutableList<City> = mutableListOf(),

    @CreationTimestamp
    var createdAt: Timestamp?=null,

    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)
