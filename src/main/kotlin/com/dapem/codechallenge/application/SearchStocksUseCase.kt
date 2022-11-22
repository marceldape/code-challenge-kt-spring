package com.dapem.codechallenge.application

import com.dapem.codechallenge.domain.Stock
import com.dapem.codechallenge.domain.interfaces.StockRepository
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol

class SearchStocksUseCase(
    private val stockRepository: StockRepository
) {
    fun execute(symbols: List<Symbol>, interval: Interval): List<Stock> =
        stockRepository.search(symbols = symbols, interval = interval)
}