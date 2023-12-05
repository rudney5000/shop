package com.shop.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var description: String,
    var phone: String,
    var address: String,
    var name: String,
    var ref: String,
    var email: String,

    @Column(name = "user-id")
    var userId: Long,

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnore
    var user: User,

    @ManyToMany
    @JoinTable(
        name = "ActivityArea_Company",
        joinColumns = [JoinColumn(name = "activity_area_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    var activityAreas: MutableSet<ActivityArea> = mutableSetOf()
)
