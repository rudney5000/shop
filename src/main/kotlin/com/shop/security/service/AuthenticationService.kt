package com.shop.security.service

import com.shop.security.auth.AuthenticationRequest
import com.shop.security.auth.AuthenticationResponse
import com.shop.security.auth.RegisterRequest

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse
}