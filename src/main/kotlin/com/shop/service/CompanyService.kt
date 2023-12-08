package com.shop.service

import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto
import java.util.Optional

interface CompanyService {

    fun getAllCompanies(): ResponseDto<List<CompanyRequest>>

    fun getOneCompany(id: Long): Optional<ResponseDto<CompanyRequest>>

    fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyRequest>

    fun updateCompany(id: Long, companyRequest: CompanyRequest):Optional<ResponseDto<CompanyRequest>>

    fun deleteCompany(id: Long)
}