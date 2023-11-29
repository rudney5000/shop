package com.shop.mapper

import com.shop.dto.CompanyDto
import com.shop.dto.CompanyRequest
import com.shop.entity.Company
import org.mapstruct.Mapper

@Mapper
interface CompanyMapper {
    fun toDto(company: Company): CompanyDto

    fun toEntity(companyRequest: CompanyRequest): Company
}