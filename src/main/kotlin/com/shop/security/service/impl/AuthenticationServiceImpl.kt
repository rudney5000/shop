package com.shop.security.service.impl

import com.shop.security.auth.AuthenticationRequest
import com.shop.security.auth.AuthenticationResponse
import com.shop.security.auth.RegisterRequest
import com.shop.security.entity.Role
import com.shop.security.entity.User
import com.shop.security.repository.UserRepository
import com.shop.security.service.AuthenticationService
import com.shop.security.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private var repository: UserRepository,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager
): AuthenticationService {
    override fun register(request: RegisterRequest): AuthenticationResponse {
        val user = User(
            name = request.name,
            password = passwordEncoder.encode(request.password),
            email = request.email,
            roles = Role.USER
        )

        repository.save(user)
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }

    override fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val user = repository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }

}