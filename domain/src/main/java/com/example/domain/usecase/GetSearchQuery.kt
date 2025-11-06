package com.example.domain.usecase

import com.example.domain.repo.SearchPrefsRepository
import kotlinx.coroutines.flow.Flow

class GetSearchQuery(
    private val repository: SearchPrefsRepository
) {
    operator fun invoke(): Flow<String> = repository.searchQueryFlow
}