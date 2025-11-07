package com.example.flickrphoto.navigation

import kotlinx.serialization.Serializable

@Serializable
object PhotoSearchScreen

@Serializable
data class PhotoDetailScreen(
    val photoId: String,
    val secret: String = ""
)