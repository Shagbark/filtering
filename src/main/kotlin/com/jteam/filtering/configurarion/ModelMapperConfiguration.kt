package com.jteam.filtering.configurarion

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ModelMapperConfiguration {

    @Bean
    open fun modelMapper() : ModelMapper  {
        return ModelMapper()
    }

}