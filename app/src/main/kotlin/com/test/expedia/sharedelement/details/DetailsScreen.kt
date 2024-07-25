package com.test.expedia.sharedelement.details

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    url: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.e("Ashok", "Detail SharedTransitionScope = $this")
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .sharedElement(
                    rememberSharedContentState(key = "image-$url"),
                    animatedVisibilityScope,
                ),
            contentDescription = null,
        )
        DetailedDescription(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .sharedBounds(
                    rememberSharedContentState(
                        key = "text-$url"
                    ),
                    animatedVisibilityScope,
                ),
            url = url,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailedDescription(
    modifier: Modifier = Modifier,
    url: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Column(modifier = modifier) {
        Text(
            text = "My name is Ashok My name is Ashok My name is Ashok My name is Ashok My name is Ashok My name is Ashok",
            /*modifier = modifier.sharedBounds(
                rememberSharedContentState(
                    key = "text-$url"
                ),
                animatedVisibilityScope,
            )*/
            style = MaterialTheme.typography.headlineMedium,
            overflow = TextOverflow.Ellipsis,
        )

//        Text(
//            text = "This is the test detail description of the component and this is how its transformed",
//            modifier = modifier,
//            style = MaterialTheme.typography.bodyLarge,
//            overflow = TextOverflow.Ellipsis,
//        )
    }
}