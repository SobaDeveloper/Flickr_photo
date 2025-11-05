package com.example.core.util

import com.example.core.model.PhotoSize

object PhotoUtils {

    fun buildImageUrl(
        server: String,
        id: String,
        secret: String,
        size: PhotoSize = PhotoSize.LARGE
    ): String {
        return if (size.suffix.isNotEmpty()) {
            "https://live.staticflickr.com/$server/${id}_${secret}_${size.suffix}.jpg"
        } else {
            "https://live.staticflickr.com/$server/${id}_${secret}.jpg"
        }
    }
}