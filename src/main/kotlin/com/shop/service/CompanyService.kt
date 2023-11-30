package com.shop.service

import com.shop.dto.CompanyDto
import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto
import java.util.Optional

interface CompanyService {

    fun getOneCompany(id: Long): Optional<ResponseDto<CompanyDto>>

    fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyDto>

//    fun updateCompany(id: Long, companyRequest: CompanyRequest):Optional<ResponseDto<CompanyDto>>

    fun deleteCompany(id: Long)
}