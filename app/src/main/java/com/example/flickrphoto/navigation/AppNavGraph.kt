package com.example.flickrphoto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.photos.details.screens.PhotoDetailsScreen
import com.example.photos.search.screens.PhotoSearchScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = PhotoSearchScreen
    ) {
        composable<PhotoSearchScreen> {
            PhotoSearchScreen(
                onPhotoClick = { photo ->
                    navController.navigate(
                        PhotoDetailScreen(
                            photoId = photo.id,
                            secret = photo.secret
                        )
                    )
                }
            )
        }

        composable<PhotoDetailScreen> { backStackEntry ->
            val photoDetail = backStackEntry.arguments?.let {
                PhotoDetailScreen(
                    photoId = it.getString("photoId") ?: "",
                    secret = it.getString("secret") ?: ""
                )
            } ?: return@composable

            PhotoDetailsScreen(
                photoId = photoDetail.photoId,
                secret = photoDetail.secret,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}