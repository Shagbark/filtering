package com.jteam.filtering.api

import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.service.JobService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/job")
class JobController(private val jobService: JobService) {

    @GetMapping
    fun findAllJobs() : List<Job> {
        return jobService.findAllJobs()
    }

}