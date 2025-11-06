package com.example.domain.usecase

import com.example.domain.models.Photo
import com.example.domain.models.Photos
import com.example.domain.models.PhotosResponse
import com.example.domain.repo.PhotosRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRecentPhotosTest {

    private lateinit var repository: PhotosRepository
    private lateinit var getRecentPhotos: GetRecentPhotos

    @Before
    fun setUp() {
        repository = mockk()
        getRecentPhotos = GetRecentPhotos(repository)
    }

    @Test
    fun testGetRecentPhotosSuccess() = runTest {
        val mockPhotos = listOf(mockk<Photo>())
        val photosResponse = PhotosResponse(
            photos = Photos(
                page = 1,
                pages = 1,
                perPage = 20,
                total = 1,
                photoList = mockPhotos
            )
        )
        coEvery { repository.getRecentPhotos(perPage = 20, page = 1) } returns Result.success(photosResponse)

        val result = getRecentPhotos(perPage = 20, page = 1)

        assertTrue(result.isSuccess)
        assertEquals(mockPhotos, result.getOrNull())
    }

    @Test
    fun testGetRecentPhotosFailure() = runTest {
        val exception = Exception("Network error")
        coEvery { repository.getRecentPhotos(perPage = 20, page = 1) } returns Result.failure(exception)

        val result = getRecentPhotos(perPage = 20, page = 1)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}