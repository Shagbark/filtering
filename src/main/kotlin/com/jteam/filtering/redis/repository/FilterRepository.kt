package com.jteam.filtering.redis.repository

interface FilterRepository {

    fun createFilter(filterValue: String) : String

}