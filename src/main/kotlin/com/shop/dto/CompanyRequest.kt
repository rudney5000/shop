package com.shop.dto

data class CompanyRequest(
    private var description: String,
    private var phone: String,
    private var address: String,
    private var name: String,
    private var ref: String,
    private var email: String,
)
