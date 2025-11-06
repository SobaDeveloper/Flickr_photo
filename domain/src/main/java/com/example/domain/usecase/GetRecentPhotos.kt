package com.example.domain.usecase

import com.example.domain.models.Photo
import com.example.domain.repo.PhotosRepository

class GetRecentPhotos(
    private val repository: PhotosRepository
) {
    suspend operator fun invoke(
        perPage: Int = 20,
        page: Int = 1
    ): Result<List<Photo>> = repository.getRecentPhotos(perPage, page).map { response -> response.photos.photoList }
}