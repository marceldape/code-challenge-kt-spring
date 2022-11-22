package com.dapem.codechallenge.application

import com.dapem.codechallenge.domain.interfaces.CompaniesRepository
import com.dapem.codechallenge.domain.valueobjects.Symbol
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetCompaniesUseCaseTest {

    @MockK
    private lateinit var companiesRepository: CompaniesRepository

    @InjectMockKs
    private lateinit var getCompaniesUseCase: GetCompaniesUseCase

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when search then return a list of Symbols`() {

        val companies = listOf(Symbol("XPEV"), Symbol("MSFT"), Symbol("AMZN"))

        every {
            companiesRepository.search()
        }.returns(companies)

        val results = getCompaniesUseCase.execute()

        results.forEach { companies.contains(element = it) }
        assertEquals(expected = 3, actual = companies.size)
    }

}