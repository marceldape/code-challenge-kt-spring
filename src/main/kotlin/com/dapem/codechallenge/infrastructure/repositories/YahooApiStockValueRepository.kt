package com.dapem.codechallenge.infrastructure.repositories

import com.dapem.codechallenge.domain.Stock
import com.dapem.codechallenge.domain.exceptions.CompanyNotFound
import com.dapem.codechallenge.domain.interfaces.StockRepository
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol
import yahoofinance.YahooFinance
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*
import yahoofinance.histquotes.Interval as YahooInterval

class YahooApiStockValueRepository : StockRepository {

    override fun search(symbols: List<Symbol>, interval: Interval): List<Stock> {

        try {
            val stocks: Map<String, yahoofinance.Stock> = YahooFinance.get(
                symbols.map { it.value }.toTypedArray(),
                getStartDate(period = interval),
                YahooInterval.valueOf(
                    interval.toString()
                )
            )

            return stocks.map { stock ->
                Stock(
                    symbol = Symbol(stock.key),
                    interval = interval,
                    value = stock.value.history.map {
                        mapOf(
                            SimpleDateFormat("dd/MM/yyyy")
                                .format(it.date.time) to it.low.toFloat()
                        )
                    }
                )
        }

        } catch(e: FileNotFoundException){
             throw CompanyNotFound("No details found for ${e.message?.substringAfterLast("/")?.substringBeforeLast("?")}")
        }

    }

    private fun getStartDate(period: Interval): Calendar {
        val cal = Calendar.getInstance()
        when (period) {
            Interval.DAILY -> cal.add (Calendar.DAY_OF_MONTH, -7)
            Interval.WEEKLY -> cal.add(Calendar.WEEK_OF_MONTH, -3)
            Interval.MONTHLY -> cal.add(Calendar.MONTH, -5)
        }
        return cal
    }

}