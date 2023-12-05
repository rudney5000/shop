package com.shop.repository

import com.shop.dto.ActivityAreaRequest
import com.shop.entity.ActivityArea
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActivityAreaRepository:JpaRepository<ActivityArea, Long> {
    fun findByName(name: String): ActivityArea?
}