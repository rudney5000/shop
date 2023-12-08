package com.shop.utils

data class Pageable<T>(
    val pageable: CustomPageable? = null,
    val data: List<T>? = null
)