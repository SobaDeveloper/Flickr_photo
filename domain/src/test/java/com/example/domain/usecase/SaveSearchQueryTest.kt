package com.example.domain.usecase

import com.example.domain.repo.SearchPrefsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveSearchQueryTest {

    private lateinit var repository: SearchPrefsRepository
    private lateinit var saveSearchQuery: SaveSearchQuery

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        saveSearchQuery = SaveSearchQuery(repository)
    }

    @Test
    fun testSaveSearchQueryCallsRepository() = runTest {
        val query = "sunsets"

        saveSearchQuery(query)

        coVerify { repository.saveSearchQuery(query) }
    }
}