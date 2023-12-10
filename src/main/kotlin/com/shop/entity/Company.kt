package com.shop.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "_companies")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var description: String,
    var phone: String,
    var address: String,
    var name: String,
    var ref: String,
    var email: String,

    @Column(name = "user-id")
    var userId: Long,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var users: User,

    @ManyToMany
    @JoinTable(
        name = "ActivityArea_Company",
        joinColumns = [JoinColumn(name = "activity_area_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    var activityAreas: MutableSet<ActivityArea> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "City_Company",
        joinColumns = [JoinColumn(name = "city_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    var cities: MutableSet<City> = mutableSetOf(),

    @CreationTimestamp
    var created: Timestamp? = null,
)
