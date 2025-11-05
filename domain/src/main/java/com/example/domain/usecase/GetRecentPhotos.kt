package com.example.domain.usecase

import com.example.domain.models.PhotosResponse
import com.example.domain.repo.PhotosRepository

class GetRecentPhotos(
    private val repository: PhotosRepository
) {
    suspend operator fun invoke(
        perPage: Int = 20,
        page: Int = 1
    ): Result<PhotosResponse> {
        return repository.getRecentPhotos(perPage, page)
    }
}