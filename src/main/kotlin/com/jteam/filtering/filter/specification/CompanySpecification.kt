package com.jteam.filtering.filter.specification

import com.jteam.filtering.domain.JobEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.*

class CompanySpecification : Specification<JobEntity> {

    override fun toPredicate(
        root: Root<JobEntity>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {

        root.get<Path<JobEntity>>("companyName")

        // criteriaBuilder.`in`(root.get(''))
        return criteriaBuilder.equal(root.get<Path<JobEntity>>("companyName"), "")
    }


}