package com.jteam.filtering.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jteam.filtering.domain.JobEntity
import com.jteam.filtering.domain.dto.Filter
import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.repository.JobRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.persistence.criteria.Path

@Service
open class JobFilterService(private val jobRepository: JobRepository, private val objectMapper: ObjectMapper) {

    fun getFilteredJobList(filterValue : String) : Collection<Job> {
        val filterObject : Filter = objectMapper.readValue(filterValue, Filter::class.java)
        createFilterQuery(filterObject)
        return emptyList()
    }

    private fun createFilterQuery(filter : Filter) {
        val filterSpecifications : MutableList<Specification<JobEntity>> = ArrayList()
        if (!filter.companies.isNullOrEmpty()) {
            filterSpecifications.add(getStringInSpecification("companyName", filter.companies))
        }

        if (!filter.vacancies.isNullOrEmpty()) {
            filterSpecifications.add(getStringInSpecification("vacancyName", filter.vacancies))
        }

        if (filterSpecifications.isNotEmpty()) {
            val specification = Specification.where(filterSpecifications[0])
            if (filterSpecifications.size > 1) {
                for (i in 1 until filterSpecifications.size) {
                    specification!!.and(filterSpecifications[i])
                }
            }
            val list = jobRepository.findAll(specification)
            list.forEach { println(it) }
        }
    }

    private fun getFilterSpecification() : Specification<JobEntity> {
        var specification : Specification<JobEntity>?

    }

    private fun addSpecification(
        specification: Specification<JobEntity>?,
        fieldName: String,
        values: Collection<String>?
    ) : Specification<JobEntity> {
        if (!values.isNullOrEmpty()) {

        }
    }

    private fun getStringInSpecification(fieldName : String, values : Collection<String>?) : Specification<JobEntity> {
        return Specification<JobEntity> { root, _, _ -> root.get<Path<JobEntity>>(fieldName).`in`(values)}
    }

}