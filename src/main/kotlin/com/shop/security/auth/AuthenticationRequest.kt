package com.shop.security.auth

data class AuthenticationRequest(
    var email: String,
    var password: String
)
