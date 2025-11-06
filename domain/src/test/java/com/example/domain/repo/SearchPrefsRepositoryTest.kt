package com.example.domain.repo

import com.example.data.local.SearchPrefsManager
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchPrefsRepositoryTest {
    private lateinit var searchPrefsManager: SearchPrefsManager
    private lateinit var repository: SearchPrefsRepository

    @Before
    fun setUp() {
        searchPrefsManager = mockk(relaxed = true)
        repository = SearchPrefsRepository(searchPrefsManager)
    }

    @Test
    fun testSearchQueryFlowEmitsValue() = runTest {
        val expected = "flowers"
        every { searchPrefsManager.searchQueryFlow } returns flowOf(expected)
        repository = SearchPrefsRepository(searchPrefsManager)

        val value = repository.searchQueryFlow.first()

        assertEquals(expected, value)
    }

    @Test
    fun testSaveSearchQueryDelegatesToManager() = runTest {
        val query = "mountains"

        repository.saveSearchQuery(query)

        coVerify { searchPrefsManager.saveSearchQuery(query) }
    }
}
