package com.jteam.filtering.redis.repository

interface FilterRepository {

    fun createFilter(filter: String) : String

}