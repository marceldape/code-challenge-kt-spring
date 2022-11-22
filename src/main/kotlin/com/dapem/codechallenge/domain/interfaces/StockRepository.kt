package com.dapem.codechallenge.domain.interfaces

import com.dapem.codechallenge.domain.Stock
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol

interface StockRepository {
    fun search(symbols: List<Symbol>, interval: Interval): List<Stock>
}