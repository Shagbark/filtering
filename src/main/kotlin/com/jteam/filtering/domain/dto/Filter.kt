package com.jteam.filtering.domain.dto

import com.jteam.filtering.domain.Currency

data class Filter(
    var id : String? = null,
    var vacancies : Collection<String> = ArrayList(),
    var companies : Collection<String> = ArrayList(),
    var salary : FilterBetweenValue,
    var currency : Collection<Currency> = ArrayList()
)