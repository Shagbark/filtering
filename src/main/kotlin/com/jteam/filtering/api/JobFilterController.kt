package com.jteam.filtering.api

import com.jteam.filtering.domain.dto.Job
import com.jteam.filtering.filter.FilterService
import com.jteam.filtering.service.JobFilterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/job/filter")
class JobFilterController(private val filterService: FilterService, private val jobFilterService: JobFilterService) {

    @PostMapping
    fun createFilter(@RequestBody filterValue : String, response: HttpServletResponse) {
        val filterId = filterService.createFilter(filterValue)

        response.setHeader("Location", "/api/job/filter?filterId=$filterId")
        response.status = HttpStatus.MOVED_PERMANENTLY.value()
    }

    @GetMapping
    fun getValuesByFilter(@RequestParam filterId : String) : Collection<Job> {
        val filterValue = filterService.extractFilterJson(filterId)
        return jobFilterService.getFilteredJobList(filterValue)
    }

}