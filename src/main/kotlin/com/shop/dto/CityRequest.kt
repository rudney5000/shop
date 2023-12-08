package com.shop.dto

import com.shop.entity.City

data class CityRequest(
    var name: String,
    var countryId: Long,
)

fun City.toCityRequest():CityRequest =
    CityRequest(
        name = this.name,
        countryId = this.countryId
    )

//fun CityRequest.toCity():City =
//    City(
//        name = this.name,
//        countryId = this.countryId
//    )