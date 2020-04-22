package com.jteam.filtering.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jteam.filtering.domain.Currency
import com.jteam.filtering.domain.JobEntity
import com.jteam.filtering.domain.dto.Filter
import com.jteam.filtering.domain.dto.FilterBetweenValue
import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.repository.JobRepository
import org.modelmapper.ModelMapper
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.persistence.criteria.*

@Service
open class JobFilterService(private val jobRepository: JobRepository,
                            private val modelMapper: ModelMapper,
                            private val objectMapper: ObjectMapper) {

    fun getFilteredJobList(filterValue : String) : Collection<Job> {
        val filterObject : Filter = objectMapper.readValue(filterValue, Filter::class.java)
        val jobs = findJobs(filterObject)

        return jobs.map { modelMapper.map(it, Job::class.java) }
    }

    private fun findJobs(filter : Filter) : Collection<JobEntity> {
        val specification : Specification<JobEntity>? = getFilterSpecifications(filter)

        return if (specification == null) jobRepository.findAll() else jobRepository.findAll(specification)
    }

    private fun getFilterSpecifications(filter: Filter) : Specification<JobEntity>? {
        var specification : Specification<JobEntity>? = null

        specification = addOrCreateSpecification(specification, "companyName", filter.companies)
        specification = addOrCreateSpecification(specification, "vacancyName", filter.vacancies)
        specification = addOrCreateSpecification(specification, "salary", filter.salary)
        specification = addOrCreateEnumSpecification(specification, filter.currency)

        return specification
    }

    private fun addOrCreateSpecification(
        specification: Specification<JobEntity>?,
        fieldName: String,
        values: Collection<String>?
    ) : Specification<JobEntity>? {
        if (!values.isNullOrEmpty()) {
            return if (specification != null) {
                specification.and(getStringInSpecification(fieldName, values))
            } else {
                Specification.where(getStringInSpecification(fieldName, values))
            }
        }
        return specification
    }

    private fun addOrCreateSpecification(
        specification: Specification<JobEntity>?,
        fieldName: String,
        salary: FilterBetweenValue?
    ) : Specification<JobEntity>? {
        if (salary != null) {
            return if (specification != null) {
                specification.and(getBetweenSpecification(fieldName, salary))
            } else {
                Specification.where(getBetweenSpecification(fieldName, salary))
            }
        }
        return specification
    }

    private fun addOrCreateEnumSpecification(
        specification: Specification<JobEntity>?,
        currency: Collection<Currency>?
    ) : Specification<JobEntity>? {
        if (!currency.isNullOrEmpty()) {
            return if (specification != null) {
                specification.and(getEnumSpecification(currency))
            } else {
                Specification.where(getEnumSpecification(currency))
            }
        }
        return specification
    }

    private fun getStringInSpecification(fieldName : String, values : Collection<String>?) : Specification<JobEntity> {
        return Specification<JobEntity> { root, _, _ -> root.get<Path<JobEntity>>(fieldName).`in`(values)}
    }

    private fun getBetweenSpecification(fieldName: String, salary: FilterBetweenValue) : Specification<JobEntity> {
        return Specification<JobEntity> { root, _, criteriaBuilder -> criteriaBuilder.between(root.get(fieldName), salary.from, salary.to) }
    }

    private fun getEnumSpecification(currency: Collection<Currency>) : Specification<JobEntity> {
        return Specification<JobEntity> { root, _, criteriaBuilder ->
            var predicate = criteriaBuilder.conjunction()
            val field = root.get<Currency>("currency")

            currency.forEach { predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get<Currency>("currency"), it)) }

            predicate
        }
    }

}