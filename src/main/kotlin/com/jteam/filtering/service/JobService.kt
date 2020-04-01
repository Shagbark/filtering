package com.jteam.filtering.service

import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.repository.JobRepository
import lombok.RequiredArgsConstructor
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
open class JobService(private val jobRepository: JobRepository, private val modelMapper: ModelMapper) {

    fun findAllJobs() : List<Job> {
        val jobs = jobRepository.findAll()
        return jobs.map { modelMapper.map(it, Job::class.java) }
    }

}