package com.shop.service

import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto
import com.shop.utils.Pageable
import java.util.Optional

interface CompanyService {

    fun getAllCompanies(
        size: Optional<Int?>?,
        page: Optional<Int?>?,
        sort: Optional<String?>?,
        filter: String?
    ): List<ResponseDto<Pageable<CompanyRequest>>>

    fun getOneCompany(id: Long): Optional<ResponseDto<CompanyRequest>>

    fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyRequest>

    fun updateCompany(id: Long, companyRequest: CompanyRequest):Optional<ResponseDto<CompanyRequest>>

    fun deleteCompany(id: Long)
}