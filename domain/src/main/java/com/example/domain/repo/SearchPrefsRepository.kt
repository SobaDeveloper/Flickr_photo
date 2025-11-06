package com.example.domain.repo

import com.example.data.local.SearchPrefsManager
import kotlinx.coroutines.flow.Flow

class SearchPrefsRepository(
    private val searchPrefsManager: SearchPrefsManager
) {

    val searchQueryFlow: Flow<String> = searchPrefsManager.searchQueryFlow

    suspend fun saveSearchQuery(query: String) {
        searchPrefsManager.saveSearchQuery(query)
    }
}