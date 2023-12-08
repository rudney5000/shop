package com.shop.dto

import com.shop.entity.City
import com.shop.entity.Country

data class CountryRequest(
    var name:String,
    var cities: MutableList<City> = mutableListOf()
)

fun CountryRequest.toCountryEntity():Country =
    Country(
        name = this.name,
        cities = this.cities
    )