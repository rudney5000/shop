package com.shop.service.impl

import com.shop.dto.CountryRequest
import com.shop.dto.ResponseDto
import com.shop.dto.toCountryEntity
import com.shop.entity.Country
import com.shop.repository.CountryRepository
import com.shop.service.CountryService
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository
):CountryService {
    override fun addCountry(countryRequest: CountryRequest): ResponseDto<Country> {
        val country = countryRequest.toCountryEntity()

        val saveCountry = countryRepository.save(country)

        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = saveCountry
        )
    }
}