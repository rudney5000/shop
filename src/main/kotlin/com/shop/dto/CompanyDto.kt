package com.shop.dto

import com.shop.entity.ActivityArea

data class CompanyDto(
    var description: String,
    var phone: String,
    var address: String,
    var name: String,
    var ref: String,
    var email: String,
    var userId: Long,
    var activityAreas: MutableSet<ActivityArea> = mutableSetOf()
)
