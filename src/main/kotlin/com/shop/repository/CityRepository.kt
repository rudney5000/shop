package com.shop.repository

import com.shop.entity.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository:JpaRepository<City, Long> {
}