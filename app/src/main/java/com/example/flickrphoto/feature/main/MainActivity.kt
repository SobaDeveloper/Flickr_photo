package com.example.flickrphoto.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.flickrphoto.feature.photosearch.PhotoSearchScreen
import com.example.flickrphoto.navigation.FlickrNavigation
import com.example.flickrphoto.ui.theme.FlickrPhotoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrPhotoTheme {
                FlickrNavigation()
            }
        }
    }
}