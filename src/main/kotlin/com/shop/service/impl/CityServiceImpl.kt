package com.shop.service.impl

import com.shop.dto.CityRequest
import com.shop.dto.ResponseDto
import com.shop.dto.ResponseError
import com.shop.dto.toCityRequest
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
    override fun getAllCities(): ResponseDto<List<CityRequest>> {
        val city = cityRepository.findAll()
        val cityRequest = city.map { it.toCityRequest() }
        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = cityRequest
        )
    }

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
            countries = country,
            companies = cityRequest.companies.toMutableList()
        )

        val savedCity = cityRepository.save(city)

        val cityDto = CityRequest(
            name = savedCity.name,
            countryId = savedCity.countryId,
            companies = savedCity.companies
        )
        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = cityDto
        )
    }
}