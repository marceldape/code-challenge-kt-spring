package com.dapem.codechallenge.application

import com.dapem.codechallenge.domain.interfaces.CompaniesRepository
import com.dapem.codechallenge.domain.valueobjects.Symbol

class GetCompaniesUseCase(
    private val companiesRepository: CompaniesRepository
) {
    fun execute(): List<Symbol> = companiesRepository.search()
}