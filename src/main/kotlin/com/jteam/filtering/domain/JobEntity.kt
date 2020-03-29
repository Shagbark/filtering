package com.jteam.filtering.domain

import javax.persistence.*

@Entity
@Table(name = "jobs")
data class JobEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "vacancy_name")
    val vacancyName: String,

    @Column(name = "company_name")
    val companyName: String,

    val salary: Int,

    val currency: Currency

)