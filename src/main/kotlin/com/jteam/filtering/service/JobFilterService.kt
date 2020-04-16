package com.jteam.filtering.service

import com.jteam.filtering.domain.dto.Filter
import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.repository.JobRepository
import org.springframework.stereotype.Service

@Service
open class JobFilterService(private val jobRepository: JobRepository) {

    fun getFilteredJobList(filterValue : String) : Collection<Job> {
        TODO("Implement")
    }

    fun createFilterQuery(filter : Filter) {
        TODO("Implement")
    }

}