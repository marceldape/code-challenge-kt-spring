package com.dapem.codechallenge.domain.exceptions

open class InvalidStockSymbolException constructor(s: String?) : Exception(s)