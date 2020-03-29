package com.jteam.filtering

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class FilterApplication

fun main(args: Array<String>) {
    SpringApplication.run(FilterApplication::class.java, *args)
}