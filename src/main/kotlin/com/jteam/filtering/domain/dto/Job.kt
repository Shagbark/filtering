package com.jteam.filtering.domain.dto

import com.jteam.filtering.domain.Currency

open class Job (
    var id: Long? = null,
    var vacancyName: String? = null,
    var companyName: String? = null,
    var salary: Int? = null,
    var currency: Currency? = null
)