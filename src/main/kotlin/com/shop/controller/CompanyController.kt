package com.shop.controller

import com.shop.dto.CompanyDto
import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto
import com.shop.service.CompanyService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
) {
    @PostMapping
    fun createCompany(@Valid @RequestBody companyRequest: CompanyRequest): ResponseEntity<ResponseDto<CompanyDto>> {
        val createCompany = companyService.createCompany(companyRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(createCompany)
    }

}