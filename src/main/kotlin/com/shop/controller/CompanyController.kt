package com.shop.controller

import com.shop.dto.CompanyRequest
import com.shop.dto.ResponseDto
import com.shop.service.CompanyService
import com.shop.utils.Pageable
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
) {

    @GetMapping
    fun getAllCompanies(
        size: Optional<Int?>?,
        page: Optional<Int?>?,
        sort: Optional<String?>?,
        filter: String?
    ): ResponseEntity<List<ResponseDto<Pageable<CompanyRequest>>>>{
        return ResponseEntity.ok(companyService.getAllCompanies(size, page, sort, filter))
    }

    @GetMapping("/{id}")
    fun getOneCompany(@PathVariable id: Long): ResponseEntity<Optional<ResponseDto<CompanyRequest>>>{
        val company = companyService.getOneCompany(id)
        return ResponseEntity.ok(company)
    }

    @PostMapping
    fun createCompany(@Valid @RequestBody companyRequest: CompanyRequest): ResponseEntity<ResponseDto<CompanyRequest>> {
        return ResponseEntity.ok(companyService.createCompany(companyRequest))

    }

    @PutMapping("/{id}")
    fun updateCompany(@PathVariable id: Long, @Valid @RequestBody companyRequest: CompanyRequest): ResponseEntity<Optional<ResponseDto<CompanyRequest>>> {
        val companyUpdate = companyService.updateCompany(id, companyRequest)
        return ResponseEntity.ok(companyUpdate)
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(@PathVariable id: Long): ResponseEntity<Void> {
        companyService.deleteCompany(id)
        return ResponseEntity.noContent().build()
    }
}