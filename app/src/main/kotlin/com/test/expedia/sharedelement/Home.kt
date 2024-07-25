package com.test.expedia.sharedelement

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.test.expedia.sharedelement.details.DetailsScreen
import com.test.expedia.sharedelement.list.ListScreen
import java.net.URLDecoder
import java.net.URLEncoder

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideInHorizontally { it } + fadeIn() },
            exitTransition = { slideOutHorizontally { -it } + fadeOut() },
            popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
            popExitTransition = { slideOutHorizontally { it } + fadeOut() },
        ) {
            composable(
                route = "list"
            ) {
                val context = LocalContext.current
                ListScreen(
                    animatedVisibilityScope = this@composable,
                    onItemClick = { item ->
                        val encoded = URLEncoder.encode(item, "UTF-8")
//                        navController.navigate("details/$encoded")
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("expedia://details/$encoded")
                        )
                        context.startActivity(intent)
//                        navController.navigate(Uri.parse("expedia://details/$encoded"))
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable(
                route = "details/{url}",
                deepLinks = listOf(navDeepLink {
                    uriPattern = "expedia://details/{url}"
                    action = Intent.ACTION_VIEW
                })
            ) { backstackEntry ->
                val encoded = backstackEntry.arguments?.getString("url") ?: error("No URL")
                val url = URLDecoder.decode(encoded, "UTF-8")
                DetailsScreen(
                    url = url,
                    animatedVisibilityScope = this@composable,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
