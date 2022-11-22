package com.dapem.codechallenge.domain.valueobjects

import com.dapem.codechallenge.domain.exceptions.InvalidIntervalException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntervalTest {

    @Test
    fun `given a set of correct Intervals, when creating Interval then a new instance is created`() {
        listOf("DAILY", "WEEKLY", "MONTHLY", "daily", "weekly", "monthly")
            .forEach {
                val inter = Interval.fromString(it)
                assertEquals(expected = it.uppercase(), actual = inter.toString())
            }
    }

    @Test
    fun `given a set of incorrect Intervals, when creating Interval then an exception is thrown`() {
        listOf("YEARLY", "INCORRECT")
            .forEach {
                Assertions.assertThrows(InvalidIntervalException::class.java) {
                    Interval.fromString(value = it)
                }
            }
    }

}



