package com.dapem.codechallenge.repositories

import com.dapem.codechallenge.infrastructure.repositories.InMemoryCompaniesRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InMemorySymbolRepositoryTest {

    private val inMemoryCompaniesRepository = InMemoryCompaniesRepository()

    @Test
    fun `when search then return a 3 stock symbols`() {
        val result = inMemoryCompaniesRepository.search()
        assertEquals(expected = 3, actual = result.size)
    }


}



