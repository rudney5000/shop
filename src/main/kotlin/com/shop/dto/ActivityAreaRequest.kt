package com.shop.dto

import com.shop.entity.ActivityArea
import com.shop.entity.Company

data class ActivityAreaRequest(
    var name: String,
    var companies: MutableList<Company> = mutableListOf()
)

fun ActivityArea.toActivityAreaRequest():ActivityAreaRequest =
    ActivityAreaRequest(
        name = this.name,
        companies = this.companies.toMutableList()
    )