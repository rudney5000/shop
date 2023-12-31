package com.shop.service.impl

import com.shop.dto.*
import com.shop.entity.ActivityArea
import com.shop.entity.City
import com.shop.entity.Company
import com.shop.entity.User
import com.shop.mapper.CompanyMapper
import com.shop.repository.ActivityAreaRepository
import com.shop.repository.CompanyRepository
import com.shop.repository.UserRepository
import com.shop.service.CompanyService
import com.shop.utils.CustomPage
import com.shop.utils.Mapper
import com.shop.utils.Pageable
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyServiceImpl(
    @Value("12")
    private var pageSize: Int = 0,
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
//    private val activityAreaRequest: ActivityAreaRequest,
    private val activityAreaRepository: ActivityAreaRepository,
//    private val companyMapper: CompanyMapper
): CompanyService {
    override fun getAllCompanies(
        size: Optional<Int?>?,
        page: Optional<Int?>?,
        sort: Optional<String?>?,
        filter: String?
    ): List<ResponseDto<Pageable<CompanyRequest>>> {
        val companies = companyRepository.findAll()

        val companyList = companies.map {
            companies -> CompanyRequest(
                description = companies.description,
                phone = companies.phone,
                address = companies.address,
                name = companies.name,
                ref = companies.ref,
                email = companies.email,
                userId = companies.userId,
                cities = companies.cities
            )
        }

        val customPageableCompany = CustomPage.CustomPageable(
            pageNumber = page?.orElse(0) ?: 0,
            pageSize = size?.orElse(pageSize) ?: pageSize,
            totalPages = 1,
            totalElements =  companies.size.toLong(),
            empty = companies.isEmpty()
        )

        val pageable = Pageable(
            pageable = customPageableCompany,
            data = companyList
        )
        val responseDto = ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = pageable
        )
        return listOf(responseDto)
    }

    override fun getOneCompany(id: Long): Optional<ResponseDto<CompanyRequest>> {
        val company =companyRepository.findById(id)
        return company.map { comp ->
            val companyDto = CompanyRequest(
                description =comp.description,
                phone = comp.phone,
                address = comp.address,
                name = comp.name,
                ref = comp.ref,
                email = comp.email,
                userId = comp.userId,
                activityAreas = comp.activityAreas,
                cities = comp.cities
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

    override fun createCompany(companyRequest: CompanyRequest): ResponseDto<CompanyRequest> {
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

        val cities = companyRequest.cities.map {
            City(
                id = it.id,
                name = it.name,
                countryId = it.countryId,
                countries = it.countries
            )
        }

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
            activityAreas = activityAreas,
            cities = cities.toMutableSet()
        )

        val savedCompany = companyRepository.save(company)

        val companyDto = CompanyRequest(
            description = savedCompany.description,
            phone = savedCompany.phone,
            address = savedCompany.address,
            name = savedCompany.name,
            ref = savedCompany.ref,
            email = savedCompany.email,
            userId = savedCompany.userId,
            activityAreas = savedCompany.activityAreas,
            cities = savedCompany.cities
        )
        return ResponseDto(
            code = 200,
            message = "OK",
            error = null,
            data = companyDto
        )
    }

        override fun updateCompany(id: Long, companyRequest: CompanyRequest): Optional<ResponseDto<CompanyRequest>> {
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
            company.cities = companyRequest.cities

            val updateCompany = companyRepository.save(company)

            val companyDto = CompanyRequest(
                description = updateCompany.description,
                phone = updateCompany.phone,
                address = updateCompany.address,
                name = updateCompany.name,
                ref = updateCompany.ref,
                email = updateCompany.email,
                userId = updateCompany.userId,
                activityAreas = updateCompany.activityAreas,
                cities = updateCompany.cities
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