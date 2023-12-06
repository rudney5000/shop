package com.shop.dto

import com.shop.entity.ActivityArea
import com.shop.entity.Company

data class ActivityAreaRequest(
    var name: String,
    var companies: MutableList<Company> = mutableListOf()
)

fun ActivityArea.toActivityAreaRequest():ActivityAreaRequest =
    ActivityAreaRequest(
        name,
        companies.map {
            Company(
                it.id,
                it.description,
                it.phone,
                it.address,
                it.name,
                it.ref,
                it.email,
                it.userId,
                it.user
            )
        }.toMutableList()
    )