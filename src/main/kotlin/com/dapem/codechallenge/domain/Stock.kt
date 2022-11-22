package com.dapem.codechallenge.domain

import com.dapem.codechallenge.domain.valueobjects.Interval
import com.dapem.codechallenge.domain.valueobjects.Symbol

data class Stock(val symbol: Symbol, val interval: Interval, val value: List<Map<String, Float>>)
