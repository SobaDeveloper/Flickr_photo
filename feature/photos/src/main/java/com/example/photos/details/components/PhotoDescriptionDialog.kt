package com.example.photos.details.components

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.core_ui.theme.FlickrPhotoTheme
import com.example.core_ui.theme.Spacing
import com.example.photos.R

@Composable
fun PhotoDescriptionDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.9f)
            )
        ) {
            Column(
                modifier = Modifier.padding(Spacing.md)
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(Spacing.md))
                }

                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(Spacing.md))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(R.string.photo_description_dialog_close_cta),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Photo Description Dialog")
@Composable
private fun PhotoDescriptionDialogWithTitlePreview() {
    FlickrPhotoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(0.5f)),
            contentAlignment = Alignment.Center
        ) {
            PhotoDescriptionDialog(
                title = "Beautiful Sunset at the Beach",
                description = "This stunning photograph captures the golden hour as the sun sets over the ocean. The warm hues of orange and pink paint the sky, while the gentle waves reflect the fading light. A perfect moment frozen in time.",
                onDismiss = {}
            )
        }
    }
}
