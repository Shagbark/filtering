package com.jteam.filtering.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Repository
class FilterRepositoryImpl(private val redisTemplate: RedisTemplate<String, Any>) : FilterRepository {

    companion object {
        const val WEEK_IN_DAYS = 7L
    }

    private lateinit var valueOperations : ValueOperations<String, Any>

    @PostConstruct
    fun initializeValueOperations() {
        valueOperations = redisTemplate.opsForValue()
    }

    override fun createFilter(filterValue: String): String {
        val filterKey = filterValue.hashCode().toString()
        valueOperations.set(filterKey, filterValue, WEEK_IN_DAYS, TimeUnit.DAYS)

        return filterKey
    }

}