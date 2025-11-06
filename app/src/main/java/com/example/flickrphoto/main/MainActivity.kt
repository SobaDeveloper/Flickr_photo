package com.example.flickrphoto.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.photos.navigation.FlickrNavigation
import com.example.core_ui.theme.FlickrPhotoTheme

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