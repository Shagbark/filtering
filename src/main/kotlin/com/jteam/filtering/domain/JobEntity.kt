package com.jteam.filtering.domain

import com.jteam.filtering.domain.type.PostgreSqlEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Entity
@Table(name = "jobs")
@TypeDef(
    name = "pgsql_enum",
    typeClass = PostgreSqlEnumType::class
)
data class JobEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "vacancy_name")
    val vacancyName: String,

    @Column(name = "company_name")
    val companyName: String,

    val salary: Int,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "currency")
    @Type(type = "pgsql_enum")
    val currency: Currency

)