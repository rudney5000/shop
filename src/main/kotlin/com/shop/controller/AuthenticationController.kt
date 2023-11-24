package com.shop.controller

import com.shop.dto.AuthenticationRequest
import com.shop.dto.AuthenticationResponse
import com.shop.dto.RegisterRequest
import com.shop.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthenticationResponse>{
        val response = authenticationService.register(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        val response = authenticationService.authenticate(request)
        return ResponseEntity.ok(response)
    }
}