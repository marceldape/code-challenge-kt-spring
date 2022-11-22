package com.dapem.codechallenge.application

import com.dapem.codechallenge.domain.Stock
import com.dapem.codechallenge.domain.interfaces.StockRepository
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchStocksUseCaseTest {

    @MockK
    private lateinit var stockRepository: StockRepository

    @InjectMockKs
    private lateinit var searchStocksUseCase: SearchStocksUseCase

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given a correct Symbol Stocks and daily interval, when search then return a Stock object`() {
        val symbols = listOf(Symbol("TSLA"))
        val interval = Interval.WEEKLY

        val stock = Stock(
            symbol = symbols[0],
            interval = interval,
            value = listOf(mapOf("31/10/2022" to 221.94.toFloat(), "10/11/2022" to 180.03.toFloat() ))
        )

        every {
            stockRepository.search(symbols = symbols, interval = interval)
        }.returns(listOf(stock))

        val results = searchStocksUseCase.execute(symbols = symbols, interval = interval)

        assertEquals(expected = stock, actual = results[0])
    }

}