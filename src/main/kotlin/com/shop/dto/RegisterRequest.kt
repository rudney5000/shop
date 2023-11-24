package com.shop.dto

import com.shop.entity.Role
import com.shop.entity.User

data class RegisterRequest (
    var name: String,
    var password: String,
    var email: String
)

fun RegisterRequest.toUserEntity():User = User(
    name = this.name,
    password = this.password,
    email = this.email,
    roles = Role.USER
)