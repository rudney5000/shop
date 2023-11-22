package com.shop.security.auth

import com.shop.security.entity.Role

data class RegisterRequest (
    var id: Long,
    var name: String,
    var password: String,
    var email: String
)