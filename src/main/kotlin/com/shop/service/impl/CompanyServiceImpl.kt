package com.shop.service.impl

import com.shop.dto.*
import com.shop.entity.ActivityArea
import com.shop.entity.Company
import com.shop.entity.User
import com.shop.mapper.CompanyMapper
import com.shop.repository.ActivityAreaRepository
import com.shop.repository.CompanyRepository
import com.shop.repository.UserRepository
import com.shop.service.CompanyService
import com.shop.utils.Mapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
//    private val activityAreaRequest: ActivityAreaRequest,
    private val activityAreaRepository: ActivityAreaRepository
//    private val companyMapper: CompanyMapper
): CompanyService {
    override fun getOneCompany(id: Long): Optional<ResponseDto<CompanyDto>> {
        val company =companyRepository.findById(id)
        return company.map { comp ->
            val companyDto = CompanyDto(
                description =comp.description,
                phone = comp.phone,
                address = comp.address,
                name = comp.name,
                ref = comp.ref,
                email = comp.email,
                userId = comp.userId,
                activityAreas = comp.activityAreas
            )
            ResponseDto(
                code = 200,
                message ="OK",
                data = companyDto
            )
        }
    }

//    override fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyDto> {
//
//        val user = userRepository.findById(companyRequest.userId).orElse(null)
//            ?: return ResponseDto(
//                code = 400,
//                message = "Invalid userId: ${companyRequest.userId}",
//                error = ResponseError(
//                    code = 400,
//                    message = "Not Found userId: ${companyRequest.userId}"
//                ),
//                data = null
//            )
//
//        val activityAreas = HashSet<ActivityArea>()
//
//        for (activityAreaRequest in companyRequest.activityAreas) {
//            val activityArea = activityAreaRepository.findByName(activityAreaRequest.name)
//                ?: ActivityArea(
//                    id = 0,
//                    name = activityAreaRequest.name
//                )
//            activityAreas.add(activityArea)
//        }
//
//        val company = Company(
//            id = 0,
//            description = companyRequest.description,
//            phone = companyRequest.phone,
//            address = companyRequest.address,
//            name = companyRequest.name,
//            ref = companyRequest.ref,
//            email = companyRequest.email,
//            userId = companyRequest.userId,
//            user = user,
//            activityAreas = companyRequest.activityAreas
//        )
//        val savedCompany = companyRepository.save(company)
//
//        val companyDto = CompanyDto(
//            description = savedCompany.description,
//            phone = savedCompany.phone,
//            address = savedCompany.address,
//            name = savedCompany.name,
//            ref = savedCompany.ref,
//            email = savedCompany.email,
//            userId = savedCompany.userId,
//            activityAreas = savedCompany.activityAreas
//        )
//
//        return ResponseDto(
//            code = 200,
//            message = "OK",
//            data = companyDto
//        )
////        val company = companyMapper.toEntity(companyRequest)
//
////        val savedCompany = companyRepository.save(company)
////        val companyDto = companyMapper.toDto(savedCompany)
////        return ResponseDto(
////            code = 200,
////            message = "OK",
////            data = companyDto
////        )
//
//    }

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

        val activityAreas = companyRequest.activityAreas.map {
            ActivityArea(
                id = it.id,
                name = it.name
            )
        }.toMutableSet()

        val company = Company(
            id = 0,
            description = companyRequest.description,
            phone = companyRequest.phone,
            address = companyRequest.address,
            name = companyRequest.name,
            ref = companyRequest.ref,
            email = companyRequest.email,
            userId = companyRequest.userId,
            users = user,
            activityAreas = activityAreas
        )

        val savedCompany = companyRepository.save(company)

        val companyDto = CompanyDto(
            description = savedCompany.description,
            phone = savedCompany.phone,
            address = savedCompany.address,
            name = savedCompany.name,
            ref = savedCompany.ref,
            email = savedCompany.email,
            userId = savedCompany.userId,
            activityAreas = savedCompany.activityAreas
        )
        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = companyDto
        )
    }



        override fun updateCompany(id: Long, companyRequest: CompanyRequest): Optional<ResponseDto<CompanyDto>> {
        val existingCompany = getOneCompany(id)
        if (existingCompany.isPresent) {
            val existsCompany = existingCompany.get()
            val company = Mapper.mapper(existsCompany.data, Company::class.java)

            company.description = companyRequest.description
            company.phone = companyRequest.phone
            company.address = companyRequest.address
            company.name = companyRequest.name
            company.ref = companyRequest.ref
            company.email = companyRequest.email
            company.userId = companyRequest.userId
            company.activityAreas = companyRequest.activityAreas

            val updateCompany = companyRepository.save(company)

            val companyDto = CompanyDto(
                description = updateCompany.description,
                phone = updateCompany.phone,
                address = updateCompany.address,
                name = updateCompany.name,
                ref = updateCompany.ref,
                email = updateCompany.email,
                userId = updateCompany.userId,
                activityAreas = updateCompany.activityAreas
            )

            return Optional.of(ResponseDto(
                code = 200,
                message = "OK",
                data = companyDto
            ))
        }else {
            return Optional.of(
                ResponseDto(
                    code = 400,
                    message = "Invalid userId: ${companyRequest.userId}",
                    error = ResponseError(
                        code = 400,
                        message = "Not Found userId: ${companyRequest.userId}"
                    ),
                    data = null
                )
            )
        }
    }

    override fun deleteCompany(id: Long) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id)
        }else{
            throw RuntimeException("Company not found with id: $id")
        }
    }
}