package com.shop.dto

import com.shop.entity.ActivityArea
import com.shop.entity.Company

data class CompanyRequest(
    var description: String,
    var phone: String,
    var address: String,
    var name: String,
    var ref: String,
    var email: String,
    var userId: Long,
    var activityAreas: MutableSet<ActivityArea> = mutableSetOf()
)

fun Company.toCompanyRequest() =
    CompanyRequest(
        description,
        phone,
        address,
        name,
        ref,
        email,
        userId,
        activityAreas.map {
            ActivityArea(
                it.id,
                it.name
            )
        }.toMutableSet()
    )