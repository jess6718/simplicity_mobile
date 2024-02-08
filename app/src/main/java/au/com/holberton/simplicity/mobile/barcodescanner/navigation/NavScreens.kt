package au.com.holberton.simplicity.mobile.barcodescanner.navigation

sealed class NavScreens(val route: String) {
    object ScannerPage : NavScreens("Scanner")
}