package com.shop.dto

import com.shop.entity.Company

data class ActivityAreaRequest(
    var name: String,
    var companies: MutableList<Company> = mutableListOf()
)
