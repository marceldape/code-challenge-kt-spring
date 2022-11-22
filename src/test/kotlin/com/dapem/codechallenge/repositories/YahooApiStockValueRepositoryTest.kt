package com.dapem.codechallenge.repositories

import com.dapem.codechallenge.domain.exceptions.CompanyNotFound
import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol
import com.dapem.codechallenge.infrastructure.repositories.YahooApiStockValueRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class YahooApiStockValueRepositoryTest {

    private val yahooApiStockRepo = YahooApiStockValueRepository()

    @Test
    fun `given a correct Symbol Stocks and daily interval, when search then return a Stock object`() {
        val symbols = listOf(Symbol("TSLA"), Symbol("AMZN"))
        val interval = Interval.DAILY

        val results = yahooApiStockRepo.search(symbols = symbols, interval = interval)

        for (result in results) {
            assertTrue { symbols.contains(result.symbol) }
            assertEquals(expected = interval, actual = result.interval)
            assertTrue(actual = result.value.size > 4)
            assertFalse { result.value.any { it.keys.isEmpty() || it.values.isEmpty() } }
        }
    }

    @Test
    fun `given a correct Symbol Stocks and weekly interval, when search then return a Stock object`() {
        val symbols = listOf(Symbol("AAPL"), Symbol("AMZN"))
        val interval = Interval.WEEKLY

        val results = yahooApiStockRepo.search(symbols = symbols, interval = interval)

        for (result in results) {
            assertTrue { symbols.contains(result.symbol) }
            assertEquals(expected = interval, actual = result.interval)
            assertTrue(actual = result.value.size > 2)
            assertFalse { result.value.any { it.keys.isEmpty() || it.values.isEmpty() } }
        }
    }

    @Test
    fun `given a correct Symbol Stocks and monthly interval, when search then return a Stock object`() {
        val symbol = listOf(Symbol("AMZN"))
        val interval = Interval.MONTHLY

        val result = yahooApiStockRepo.search(symbols = symbol, interval = interval)[0]

        assertEquals(expected = symbol[0], actual = result.symbol)
        assertEquals(expected = interval, actual = result.interval)
        assertTrue(actual = result.value.size > 4)
        assertFalse { result.value.any { it.keys.isEmpty() || it.values.isEmpty() } }
    }


    @Test
    fun `given an incorrect Symbol Stocks, when search then an exception is thrown`() {
        val symbol = listOf(Symbol("ABCD"))
        val interval = Interval.MONTHLY

        Assertions.assertThrows(CompanyNotFound::class.java) {
            yahooApiStockRepo.search(symbols = symbol, interval = interval)
        }
    }

    @Test
    fun `given 2 symbols but one incorrect, when search then an exception is thrown`() {
        val symbol = listOf(Symbol("ABCD"), Symbol("AMZN"))
        val interval = Interval.MONTHLY

        Assertions.assertThrows(CompanyNotFound::class.java) {
            yahooApiStockRepo.search(symbols = symbol, interval = interval)
        }
    }

}



