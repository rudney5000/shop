package com.shop.repository

import com.shop.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository:JpaRepository<Company, Long> {
}