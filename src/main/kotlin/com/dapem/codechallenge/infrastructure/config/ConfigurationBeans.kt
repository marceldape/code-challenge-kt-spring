package com.dapem.codechallenge.infrastructure.config

import com.dapem.codechallenge.application.GetCompaniesUseCase
import com.dapem.codechallenge.application.SearchStocksUseCase
import com.dapem.codechallenge.domain.interfaces.CompaniesRepository
import com.dapem.codechallenge.domain.interfaces.StockRepository
import com.dapem.codechallenge.infrastructure.repositories.InMemoryCompaniesRepository
import com.dapem.codechallenge.infrastructure.repositories.YahooApiStockValueRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigurationBeans {

    @Bean
    fun yahooApiStockValueRepositoryBean() = YahooApiStockValueRepository()

    @Bean
    fun searchStockUseCaseBean(stockRepository: StockRepository): SearchStocksUseCase =
        SearchStocksUseCase(stockRepository = stockRepository)

    @Bean
    fun inMemorySymbolRepositoryBean() = InMemoryCompaniesRepository()

    @Bean
    fun getCompaniesUseCaseBean(companiesRepository: CompaniesRepository): GetCompaniesUseCase =
        GetCompaniesUseCase(companiesRepository = companiesRepository)
}