package com.dapem.codechallenge.domain.valueobjects

import com.dapem.codechallenge.domain.exceptions.InvalidStockSymbolException

data class Symbol(val value: String) {
    init {
        guard(symbol = value)
    }

    private fun guard(symbol: String){
        if(!Regex(pattern = "[A-Z]{0,3}[.]?[A-Z]{1}", options = setOf(RegexOption.IGNORE_CASE)).matches(symbol))
            throw InvalidStockSymbolException("The stock symbol '$symbol' is not correct")
    }
}



