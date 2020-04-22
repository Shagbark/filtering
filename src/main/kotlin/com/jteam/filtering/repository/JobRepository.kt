package com.jteam.filtering.repository

import com.jteam.filtering.domain.JobEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : JpaRepository<JobEntity, Long>, JpaSpecificationExecutor<JobEntity>