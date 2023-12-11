package com.shop.controller

import com.shop.dto.CityRequest
import com.shop.dto.ResponseDto
import com.shop.entity.City
import com.shop.service.CityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cities")
class CityController(
    private val cityService: CityService
) {
    @PostMapping
    fun addCity(@RequestBody cityRequest: CityRequest): ResponseEntity<ResponseDto<CityRequest>> {
        return ResponseEntity.ok(cityService.addCity(cityRequest))
    }

    @GetMapping
    fun getAllCities(): ResponseEntity<ResponseDto<List<CityRequest>>>{
        return ResponseEntity.ok(cityService.getAllCities())
    }
}