package com.shop.dto

import com.shop.entity.ActivityArea
import com.shop.entity.City
import com.shop.entity.Company

data class CompanyRequest(
    var description: String,
    var phone: String,
    var address: String,
    var name: String,
    var ref: String,
    var email: String,
    var userId: Long,
    var activityAreas: MutableSet<ActivityArea> = mutableSetOf(),
    var cities: MutableSet<City> = mutableSetOf()
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
        }.toMutableSet(),
        cities = this.cities.map {
            City(
                id = it.id,
                name = it.name,
                countryId = it.countryId,
                countries = it.countries
            )
        }.toMutableSet()
    )

//fun CompanyRequest.toCompany(): Company =
//    Company(
//        description = this.description,
//        phone = this.phone,
//        address = this.address,
//        name = this.name,
//        ref = this.ref,
//        email = this.email,
//        userId = this.userId,
//        activityAreas = this.activityAreas.map {
//            ActivityArea(
//                it.id,
//                it.name,
//                it.companies
//            )
//        }.toMutableSet()
//    )