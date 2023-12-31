package com.shop.repository

import com.shop.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long>  {

    fun findByEmail(email: String): Optional<User>
}