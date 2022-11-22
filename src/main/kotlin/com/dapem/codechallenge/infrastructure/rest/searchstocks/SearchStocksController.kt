package com.dapem.codechallenge.infrastructure.rest.searchstocks

import com.dapem.codechallenge.application.SearchStocksUseCase
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchStocksController(
    private val searchStocksUseCase: SearchStocksUseCase
) {

    @GetMapping("/api/v1/stocks/{symbol}")
    fun searchStock(
        @PathVariable symbol: String,
        @RequestParam interval: String
    ): SearchStockResponse {

        searchStocksUseCase.execute(
            symbols = listOf(Symbol(value = symbol)),
            interval = Interval.fromString(value = interval)
        )[0].run {
            return SearchStockResponse(
                symbol = this.symbol.value,
                interval = interval,
                value = value,
            )
        }

    }

}