package com.dapem.codechallenge.infrastructure.rest.getcompanies

import com.dapem.codechallenge.application.GetCompaniesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCompaniesController(
    private val getCompaniesUseCase: GetCompaniesUseCase
) {

    @GetMapping("/api/v1/companies")
    fun searchStock(): List<String> = getCompaniesUseCase.execute().map { it.value }
}