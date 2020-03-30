package com.jteam.filtering.domain.dto

import com.jteam.filtering.domain.Currency

data class Job (
    val id: Long,
    val vacancyName: String,
    val companyName: String,
    val salary: Int,
    val currency: Currency
)