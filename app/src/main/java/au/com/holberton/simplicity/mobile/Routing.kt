package au.com.holberton.simplicity.mobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import au.com.holberton.simplicity.mobile.homepage.HomePageScreen
import au.com.holberton.simplicity.mobile.productdetails.ListingDetailsScreen
import au.com.holberton.simplicity.mobile.productlisting.ListingsScreen

// https://developer.android.com/jetpack/compose/navigation
@Composable
fun Routing() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePageScreen(navController::navigate) }
        composable("listings") { ListingsScreen(navController::navigate, navController::popBackStack) }
        composable("listingDetails/{listingId}") { ListingDetailsScreen(navController::popBackStack, it.arguments?.getString("listingId")) }
       // TODO  composable("scanner") { ScannerPage() }
    }
}
