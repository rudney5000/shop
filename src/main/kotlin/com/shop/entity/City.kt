package com.shop.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,

    @Column(name = "country-id")
    var countryId: Long,

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnore
    var countries: Country?,

    @ManyToMany(
        mappedBy = "cities"
    )
    var companies: MutableList<Company> = mutableListOf(),

    @CreationTimestamp
    var createdAt: Timestamp?=null,

    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)
