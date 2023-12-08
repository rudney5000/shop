package com.shop.service

import com.shop.dto.CountryRequest
import com.shop.dto.ResponseDto
import com.shop.entity.Country

interface CountryService {

    fun addCountry(countryRequest: CountryRequest): ResponseDto<Country>

}