package com.shop.dto

import com.shop.entity.City
import com.shop.entity.Company

data class CityRequest(
    var name: String,
    var countryId: Long,
    var companies: MutableList<Company> = mutableListOf()

)

fun City.toCityRequest():CityRequest =
    CityRequest(
        name = this.name,
        countryId = this.countryId,
        companies = this.companies.toMutableList()
    )
