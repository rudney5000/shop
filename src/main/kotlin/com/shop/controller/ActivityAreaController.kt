package com.shop.controller

import com.shop.dto.ActivityAreaRequest
import com.shop.dto.ResponseDto
import com.shop.service.ActivityAreaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/activity-areas")
class ActivityAreaController(
    private val activityAreaService: ActivityAreaService
) {

    @GetMapping
    fun findAll(): ResponseDto<List<ActivityAreaRequest>>{
        return activityAreaService.findAll()
    }

    @PostMapping
    fun add(@RequestBody activityAreaRequest: ActivityAreaRequest): ResponseDto<ActivityAreaRequest>{
        return activityAreaService.add(activityAreaRequest)
    }
}