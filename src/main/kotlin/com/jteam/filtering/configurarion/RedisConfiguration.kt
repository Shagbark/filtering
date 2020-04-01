package com.jteam.filtering.configurarion

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer

@Configuration
open class RedisConfiguration {

    @Bean
    open fun jedisConnectionFactory() : JedisConnectionFactory = JedisConnectionFactory()

    @Bean
    open fun redisTemplate(jedisConnectionFactory: JedisConnectionFactory) : RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()

        template.connectionFactory = jedisConnectionFactory
        template.valueSerializer = GenericToStringSerializer(Any::class.java)

        return template
    }

}