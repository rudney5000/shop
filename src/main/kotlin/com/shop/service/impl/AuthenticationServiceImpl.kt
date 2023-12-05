package com.shop.service.impl

import com.shop.dto.*
import com.shop.entity.User
import com.shop.repository.UserRepository
import com.shop.service.AuthenticationService
import com.shop.service.JwtService
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
        val user:User = request.toUserEntity().also {
            userObj:User -> userObj.password = passwordEncoder.encode(request.password)
        }

        repository.save(user)
        val jwtToken = jwtService.generateToken(user.toUserAuth())
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
        val jwtToken = jwtService.generateToken(user.toUserAuth())
        return AuthenticationResponse(jwtToken)
    }

}