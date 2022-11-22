package com.dapem.codechallenge.domain.interfaces

import com.dapem.codechallenge.domain.valueobjects.Symbol

interface CompaniesRepository {
    fun search(): List<Symbol>
}