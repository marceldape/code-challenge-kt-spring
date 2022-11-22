package com.dapem.codechallenge.infrastructure.repositories

import com.dapem.codechallenge.domain.interfaces.CompaniesRepository
import com.dapem.codechallenge.domain.valueobjects.Symbol

class InMemoryCompaniesRepository : CompaniesRepository {
    companion object {
        private val POPULAR_STOCKS = listOf("TSLA", "APPL", "AMZN", "NIO", "META", "MSFT", "XPEV", "AMC", "APE", "COIN")
        private const val QUANTITY = 3
    }

    override fun search(): List<Symbol> = POPULAR_STOCKS.shuffled().take(n = QUANTITY).map { Symbol(value = it) }
}