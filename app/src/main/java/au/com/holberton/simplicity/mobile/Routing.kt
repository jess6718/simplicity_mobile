package au.com.holberton.simplicity.mobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// https://developer.android.com/jetpack/compose/navigation
@Composable
fun Routing() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listings") {
        composable("listings") { ListingsScreen(navController::navigate) }
        composable("listingDetails/{listingId}") { ListingDetailsScreen(navController::popBackStack, it.arguments?.getString("listingId")) }
    }
}
