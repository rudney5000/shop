package com.shop.service.impl

import com.shop.dto.ActivityAreaRequest
import com.shop.dto.ResponseDto
import com.shop.dto.toActivityAreaRequest
import com.shop.entity.ActivityArea
import com.shop.repository.ActivityAreaRepository
import com.shop.service.ActivityAreaService
import org.springframework.stereotype.Service

@Service
class ActivityAreaServiceImpl(
    private val activityAreaRepository: ActivityAreaRepository
): ActivityAreaService {
    override fun findAll(): ResponseDto<List<ActivityAreaRequest>> {
        val activityAreas =  activityAreaRepository.findAll()
        val activityAreaRequests = activityAreas.map { it.toActivityAreaRequest() }

        return ResponseDto(
            code = 200,
            message = "OK",
            data = activityAreaRequests
        )
    }

    override fun add(activityAreaRequest: ActivityAreaRequest): ResponseDto<ActivityAreaRequest> {
        val activityArea = ActivityArea(
            name = activityAreaRequest.name,
            companies = activityAreaRequest.companies.toMutableList()
        )
        val savedActivityArea =  activityAreaRepository.save(activityArea)
        return ResponseDto(
            code = 200,
            message = "OK",
            data = savedActivityArea.toActivityAreaRequest()
        )
    }
}