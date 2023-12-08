package com.shop.service.impl

import com.shop.dto.CityRequest
import com.shop.dto.ResponseDto
import com.shop.dto.ResponseError
import com.shop.entity.City
import com.shop.repository.CityRepository
import com.shop.repository.CountryRepository
import com.shop.service.CityService
import org.springframework.stereotype.Service

@Service
class CityServiceImpl(
    private val cityRepository: CityRepository,
    private val countryRepository: CountryRepository,
): CityService{
    override fun addCity(cityRequest: CityRequest): ResponseDto<CityRequest> {
        val country = countryRepository.findById(cityRequest.countryId).orElse(null)
            ?: return ResponseDto(
                code = 404,
                message = "Invalid countryId: ${cityRequest.countryId}",
                error = ResponseError(
                    code = 404,
                    message = "Not Found countryId: ${cityRequest.countryId}"
                ),
                data = null
            )

        val city = City(
            id = 0,
            name = cityRequest.name,
            countryId = cityRequest.countryId,
            countries = country
        )

        val savedCity = cityRepository.save(city)

        val cityDto = CityRequest(
            name = savedCity.name,
            countryId = savedCity.countryId
        )
        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = cityDto
        )
    }
}