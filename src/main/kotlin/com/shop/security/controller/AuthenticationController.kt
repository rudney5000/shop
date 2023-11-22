package com.shop.security.controller

import com.shop.security.auth.AuthenticationRequest
import com.shop.security.auth.AuthenticationResponse
import com.shop.security.auth.RegisterRequest
import com.shop.security.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
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