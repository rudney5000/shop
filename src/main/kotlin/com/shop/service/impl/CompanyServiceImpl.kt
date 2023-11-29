package com.shop.service.impl

import com.shop.dto.CompanyRequest
import com.shop.dto.CompanyDto
import com.shop.dto.ResponseDto
import com.shop.entity.Company
import com.shop.mapper.CompanyMapper
import com.shop.repository.CompanyRepository
import com.shop.service.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository,
    private val companyMapper: CompanyMapper
): CompanyService {
    override fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyDto> {
        val company = companyMapper.toEntity(companyRequest)

        val savedCompany = companyRepository.save(company)
        val companyDto = companyMapper.toDto(savedCompany)
        return ResponseDto(
            code = 200,
            message = "OK",
            data = companyDto
        )

    }

}