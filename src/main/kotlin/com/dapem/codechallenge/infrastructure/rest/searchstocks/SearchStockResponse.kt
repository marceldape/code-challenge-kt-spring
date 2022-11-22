package com.dapem.codechallenge.infrastructure.rest.searchstocks

data class SearchStockResponse(
    val symbol: String,
    val currency: String = "USD",
    val interval: String,
    val value: List<Map<String, Float>>
)
