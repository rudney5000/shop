package com.shop.service

import com.shop.dto.AuthenticationRequest
import com.shop.dto.AuthenticationResponse
import com.shop.dto.RegisterRequest

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse
}