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

fun Company.toCompanyRequest(): CompanyRequest =
    CompanyRequest(
        description = this.description,
        phone = this.phone,
        address = this.address,
        name = this.name,
        ref = this.ref,
        email = this.email,
        userId = this.userId,
        activityAreas = this.activityAreas.map {
            ActivityArea(
                id = it.id,
                name = it.name
            )
        }.toMutableSet()
    )