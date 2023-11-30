package com.shop.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    @JsonIgnore
    var password: String,

    var email: String,

    @Enumerated(EnumType.STRING)
    var roles: Role,

    @OneToMany(
        cascade =[CascadeType.ALL],
        mappedBy = "user"
    )
    var companies: MutableList<Company> = mutableListOf(),
)