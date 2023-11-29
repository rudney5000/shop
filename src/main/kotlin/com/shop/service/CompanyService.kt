package com.shop.service

import com.shop.dto.CompanyDto
import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto

interface CompanyService {

    fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyDto>
}