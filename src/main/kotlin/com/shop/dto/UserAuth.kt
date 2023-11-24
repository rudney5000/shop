package com.shop.dto

import com.shop.entity.Role
import com.shop.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserAuth(
    var name: String,
    private var password: String,
    var email: String,
    var roles: Role
):UserDetails{
    override fun getAuthorities() = listOf(SimpleGrantedAuthority(roles.name))

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}

fun UserAuth.toUserEntity():User = User(
    name = this.name,
    email = this.email,
    password = this.password,
    roles = this.roles
)

fun User.toUserAuth():UserAuth = UserAuth(
    name = this.name,
    email = this.email,
    password = this.password,
    roles = this.roles
)