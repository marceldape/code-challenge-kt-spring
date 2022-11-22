package com.dapem.codechallenge.domain.valueobjects

import com.dapem.codechallenge.domain.exceptions.InvalidIntervalException

enum class Interval {
    DAILY, WEEKLY, MONTHLY;

    companion object {
        fun fromString(value: String): Interval {
            return when (value.uppercase()) {
                "DAILY" -> DAILY
                "WEEKLY" -> WEEKLY
                "MONTHLY" -> MONTHLY
                else -> throw InvalidIntervalException("Invalid time interval. Must be 'DAILY', 'WEEKLY', or 'MONTHLY'")
            }
        }
    }
}