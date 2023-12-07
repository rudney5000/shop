package com.shop.service

import com.shop.dto.ActivityAreaRequest
import com.shop.dto.ResponseDto

interface ActivityAreaService {

    fun findAll(): ResponseDto<List<ActivityAreaRequest>>
    fun add(activityAreaRequest: ActivityAreaRequest): ResponseDto<ActivityAreaRequest>
}