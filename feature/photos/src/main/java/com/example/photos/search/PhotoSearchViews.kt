package com.example.photos.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.models.Photo
import com.example.core_ui.theme.Spacing
import com.example.photos.R

@Composable
fun PhotoSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text(stringResource(R.string.photo_search_app_bar_label)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(end = Spacing.md),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.photo_search_clear_content_description)
                    )
                }
            }
        }
    )
}

@Composable
fun PhotoGrid(
    photos: List<Photo>,
    onPhotoClick: (Photo) -> Unit,
    isLoadingMore: Boolean = false,
    onLoadMore: () -> Unit = {}
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = photos,
            key = { index, photo -> "${photo.id}_$index" }
        ) { _, photo ->
            PhotoGridItem(
                photo = photo,
                onClick = { onPhotoClick(photo) }
            )
        }

        if (isLoadingMore) {
            item(span = { GridItemSpan(3) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.md),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    // Detect when user scrolls to bottom
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null &&
                    lastVisibleIndex >= photos.size - 6 &&
                    !isLoadingMore) {
                    onLoadMore()
                }
            }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoGridItem(
    photo: Photo,
    onClick: () -> Unit
) {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            GlideImage(
                model = photo.imageUrl,
                contentDescription = photo.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PhotoSearchEmptyState(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.xl),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private val mockPhotos = List(4) { index ->
    Photo(
        id = index.toString(),
        title = "Photo $index",
        secret = "",
        imageUrl = ""
    )
}

@Preview(showBackground = true)
@Composable
fun PhotoGridPreview() {
    MaterialTheme {
        PhotoGrid(
            photos = mockPhotos,
            onPhotoClick = {}
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun PhotoGridItemPreview() {
    MaterialTheme {
        PhotoGridItem(
            photo = mockPhotos.first(),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoSearchEmptyStatePreview() {
    MaterialTheme {
        PhotoSearchEmptyState(message = "No photos matched your search.")
    }
}