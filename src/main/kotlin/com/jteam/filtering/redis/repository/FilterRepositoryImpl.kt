package com.jteam.filtering.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class FilterRepositoryImpl(private val redisTemplate: RedisTemplate<String, Any>) : FilterRepository {

    private lateinit var valueOperations : ValueOperations<String, Any>

    @PostConstruct
    fun initializeValueOperations() {
        valueOperations = redisTemplate.opsForValue()
    }

    override fun createFilter(filter: String): String {
        TODO("Not yet implemented")
    }

}