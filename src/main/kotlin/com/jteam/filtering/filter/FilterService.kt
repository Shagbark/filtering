package com.jteam.filtering.filter

import com.jteam.filtering.redis.repository.FilterRepository
import org.springframework.stereotype.Service

@Service
class FilterService(private val filterRepository: FilterRepository) {

    fun createFilter(filter : String) : String {
        require(filter.isNotBlank()) { "Filter is empty" }
        return filterRepository.createFilter(filter)
    }

    fun extractFilterJson(filterId : String) : String {
        require(filterId.isNotBlank()) { "Filter ID is empty" }
        return filterRepository.extractFilterJson(filterId)
    }

}