package com.shop.service.impl

import com.shop.dto.CompanyRequest
import com.shop.dto.CompanyDto
import com.shop.dto.ResponseDto
import com.shop.dto.ResponseError
import com.shop.entity.Company
import com.shop.mapper.CompanyMapper
import com.shop.repository.CompanyRepository
import com.shop.repository.UserRepository
import com.shop.service.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
//    private val companyMapper: CompanyMapper
): CompanyService {
    override fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyDto> {

        val user = userRepository.findById(companyRequest.userId).orElse(null)
            ?: return ResponseDto(
                code = 400,
                message = "Invalid userId: ${companyRequest.userId}",
                error = ResponseError(
                    code = 400,
                    message = "Not Found userId: ${companyRequest.userId}"
                ),
                data = null
            )

        val company = Company(
            id = 0,
            description = companyRequest.description,
            phone = companyRequest.phone,
            address = companyRequest.address,
            name = companyRequest.name,
            ref = companyRequest.ref,
            email = companyRequest.email,
            userId = companyRequest.userId,
            user = user
        )
        val savedCompany = companyRepository.save(company)

        val companyDto = CompanyDto(
            description = savedCompany.description,
            phone = savedCompany.phone,
            address = savedCompany.address,
            name = savedCompany.name,
            ref = savedCompany.ref,
            email = savedCompany.email,
            userId = savedCompany.userId
        )

        return ResponseDto(
            code = 200,
            message = "OK",
            data = companyDto
        )
//        val company = companyMapper.toEntity(companyRequest)

//        val savedCompany = companyRepository.save(company)
//        val companyDto = companyMapper.toDto(savedCompany)
//        return ResponseDto(
//            code = 200,
//            message = "OK",
//            data = companyDto
//        )

    }

}