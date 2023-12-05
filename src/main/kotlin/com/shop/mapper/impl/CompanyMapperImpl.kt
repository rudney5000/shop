package com.shop.mapper.impl

import com.shop.dto.CompanyDto
import com.shop.dto.CompanyRequest
import com.shop.entity.Company
import com.shop.mapper.CompanyMapper
import org.springframework.stereotype.Component

@Component
class CompanyMapperImpl: CompanyMapper {
    override fun toDto(company: Company): CompanyDto {
        TODO("Not yet implemented")

    }

    override fun toEntity(companyRequest: CompanyRequest): Company {
        TODO("Not yet implemented")
//         Company(
//            id = 0,
//            description = companyRequest.description,
//            phone = companyRequest.phone,
//            address = companyRequest.address,
//            name = companyRequest.name,
//            ref = companyRequest.ref,
//            email = companyRequest.email,
//            userId = companyRequest.userId,
//            user = user
//        )
    }

}