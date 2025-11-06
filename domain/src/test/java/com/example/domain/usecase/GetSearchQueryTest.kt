package com.example.domain.usecase

import com.example.domain.repo.SearchPrefsRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetSearchQueryTest {

    private lateinit var repository: SearchPrefsRepository
    private lateinit var getSearchQuery: GetSearchQuery

    @Before
    fun setUp() {
        repository = mockk()
        getSearchQuery = GetSearchQuery(repository)
    }

    @Test
    fun testGetSearchQueryEmitsValue() = runTest {
        val expectedQuery = "cats"
        every { repository.searchQueryFlow } returns flowOf(expectedQuery)

        val result: Flow<String> = getSearchQuery()
        val value = result.first()

        assertEquals(expectedQuery, value)
    }
}