package com.example.domain.usecase

import com.example.domain.repo.SearchPrefsRepository

class SaveSearchQuery(
    private val repository: SearchPrefsRepository
) {
    suspend operator fun invoke(query: String) {
        repository.saveSearchQuery(query)
    }
}