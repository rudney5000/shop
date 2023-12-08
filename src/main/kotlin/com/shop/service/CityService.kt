package com.shop.service

import com.shop.dto.CityRequest
import com.shop.dto.ResponseDto
import com.shop.entity.City

interface CityService {

    fun addCity(cityRequest: CityRequest): ResponseDto<CityRequest>
}