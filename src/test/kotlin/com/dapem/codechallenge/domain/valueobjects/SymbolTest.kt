package com.dapem.codechallenge.domain.valueobjects

import com.dapem.codechallenge.domain.exceptions.InvalidStockSymbolException
import org.junit.jupiter.api.Assertions
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SymbolTest {

    @Test
    fun `given a set of correct Symbol Stocks, when creating Symbol then a new instance is created`() {
        val correctStockSymbols = listOf("TSLA", "AMZN", "APPL", "T", "BRK.A")

        correctStockSymbols.forEach {
            val symbol = Symbol(value = it)
            assertEquals(expected = it, actual = symbol.value)
        }
    }

    @Test
    fun `given a set of incorrect Symbol Stocks, when creating Symbol then an exception is thrown`() {
        val incorrectStockSymbols = listOf("1234", "GOOG1", "invalid stock symbol", "1", "ABCDEFG")

        incorrectStockSymbols.forEach {
            Assertions.assertThrows(InvalidStockSymbolException::class.java) {
                Symbol(value = it)
            }
        }
    }

}



